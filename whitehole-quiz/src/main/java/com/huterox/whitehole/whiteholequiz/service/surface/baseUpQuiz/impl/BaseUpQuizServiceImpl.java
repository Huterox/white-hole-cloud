package com.huterox.whitehole.whiteholequiz.service.surface.baseUpQuiz.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huterox.common.utils.*;
import com.huterox.common.wordfilter.WordFilter;
import com.huterox.whitehole.whiteholequiz.entity.surface.baseUpQuiz.GetQuizAnsListEntity;
import com.huterox.whitehole.whiteholequiz.entity.surface.baseUpQuiz.GetQuizListEntity;
import com.huterox.whitehole.whiteholequiz.entity.surface.baseUpQuiz.UpAnsEntity;
import com.huterox.whitehole.whiteholequiz.entity.surface.baseUpQuiz.UpQuizEntity;
import com.huterox.whitehole.whiteholequiz.exception.BizCodeEnum;
import com.huterox.whitehole.whiteholequiz.service.base.AnsService;
import com.huterox.whitehole.whiteholequiz.service.base.QuizService;
import com.huterox.whitehole.whiteholequiz.service.surface.baseUpQuiz.BaseUpQuizService;
import com.huterox.whitehole.whiteholequiz.utils.RedisTransKey;
import com.huterox.whitehole.whiteholequiz.utils.RedisUtils;
import com.huterox.whiteholecould.entity.blog.BlogEntity;
import com.huterox.whiteholecould.entity.quiz.AnsEntity;
import com.huterox.whiteholecould.entity.quiz.QuizEntity;
import com.huterox.whiteholecould.entity.user.HeadimgEntity;
import com.huterox.whiteholecould.entity.user.LogAnsEntity;
import com.huterox.whiteholecould.entity.user.LogQuizEntity;
import com.huterox.whiteholecould.entity.user.UserEntity;
import com.huterox.whiteholecould.feign.user.FeignHeadimgService;
import com.huterox.whiteholecould.feign.user.FeignLogAnsService;
import com.huterox.whiteholecould.feign.user.FeignLogQuizService;
import com.huterox.whiteholecould.feign.user.FeignUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Service
public class BaseUpQuizServiceImpl implements BaseUpQuizService {

    @Autowired
    FeignUserService feignUserService;

    @Autowired
    FeignHeadimgService feignHeadimgService;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    QuizService quizService;

    @Autowired
    FeignLogQuizService feignLogQuizService;

    @Autowired
    FeignLogAnsService feignLogAnsService;

    @Autowired
    AnsService ansService;

    @Autowired
    WordFilter wordFilter;

    private final static Double threshold = 0.05;

    @Override
    public R BaseUpQuiz(UpQuizEntity entity) {
        String userid = entity.getUserid();
        if(redisUtils.hasKey(RedisTransKey.getBaseUpQuizKey(entity.getUserid()))){
            return R.error(BizCodeEnum.HAS_UPQUIZ.getCode(), BizCodeEnum.HAS_UPQUIZ.getMsg());
        }
        //判断用户是否存在
        String backMessage="success";
        R info = feignUserService.info(userid);
        String userString = FastJsonUtils.toJson(info.get("user"));
        UserEntity user = FastJsonUtils.fromJson(userString, UserEntity.class);
        if(user!=null){
            //这里的话我们需要开启分布式事务，但是现在目前我们没有去做集成
            QuizEntity quizEntity = new QuizEntity();
            BeanUtils.copyProperties(entity,quizEntity);
            quizEntity.setCreateTime(DateUtils.getCurrentTime());
            quizEntity.setUserNickname(user.getNickname());
            /**
             * 查看用户的头像，这里的话，我们就不去同步了
             * 我们这里用户是谁压根不重要，里面的内容是啥才重要
             * */


            R RHeadImg = feignHeadimgService.headimg(userid);
            String headImgString = FastJsonUtils.toJson(RHeadImg.get("headimg"));
            final HeadimgEntity headimg = FastJsonUtils.fromJson(headImgString, HeadimgEntity.class);
            if(headimg!=null){
                quizEntity.setUserImg(headimg.getImgpath());
            }

            /**
             * 进行铭感词过滤, 如果没有出现敏感词那么直接通过
             * 如果超过了阈值直接打回。
             * quizEntity.setStatus(1);
             **/
            String quizContent = quizEntity.getQuizContent();
            int count = wordFilter.wordCount(quizContent);
            if(count>=quizContent.length()*threshold){
                return R.error(BizCodeEnum.OVER_SENSITIVE_WORDS.getCode(),BizCodeEnum.OVER_SENSITIVE_WORDS.getMsg());
            }else if (count>0&&count<quizContent.length()*threshold){
                quizEntity.setStatus(1);
                backMessage="哇！您的提交直接通过了呢！";
            }else {
                /**
                 * 这个时候的话就是需要审核的了
                 * 同时需要把对应的铭感内容进行替换
                 */
                String replace = wordFilter.replace(quizContent, '*');
                quizEntity.setQuizContent(replace);
                quizEntity.setStatus(2);
                backMessage="您的提问已提交，正在等待审核哟！";
            }

            quizService.save(quizEntity);

            /**
             * 更新用户的操作日志
             * */
            LogQuizEntity logQuizEntity = new LogQuizEntity();
            logQuizEntity.setQuizid(quizEntity.getQuizid());
            logQuizEntity.setQuizTitle(quizEntity.getQuizTitle());
            logQuizEntity.setCreatTime(quizEntity.getCreateTime());
            logQuizEntity.setUserid(quizEntity.getUserid());
            logQuizEntity.setAction(1);
            feignLogQuizService.save(logQuizEntity);
            /**
             * 设置标志
             * */
            redisUtils.set(RedisTransKey.setBaseUpQuizKey(entity.getUserid())
                    ,1,1, TimeUnit.MINUTES
            );

        }else {
            return R.error(BizCodeEnum.NO_SUCHUSER.getCode(),BizCodeEnum.NO_SUCHUSER.getMsg());
        }
        return R.ok(backMessage);
    }

    @Override
    public R lastQuiz(GetQuizListEntity entity) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        //组装请求博文列表所需要的数据，当访问的为内部接口时，所有的参数均为Map形式
        params.put("page",entity.getPage().toString());
        params.put("limit",entity.getLimit().toString());
        params.put("accurate","single");
        //此时指定HoleNULL，那么这个key不参与查询
        params.put("table_name","HoleNULL");
        params.put("key",entity.getUserid());
        params.put("order","desc");
        params.put("status","1");
        PageUtils page = quizService.queryPage(params);
        return R.ok().put("page", page);
    }

    @Override
    public R baseUpAns(UpAnsEntity entity) {
        /**
         * 负责上传用户的回答
         * */
        String userid = entity.getUserid();
        String backMessage = "success";
        //判断用户是否存在
        if(redisUtils.hasKey(RedisTransKey.getBaseUpQuizKey(entity.getUserid()))){
            return R.error(BizCodeEnum.HAS_UPANS.getCode(), BizCodeEnum.HAS_UPANS.getMsg());
        }
        R info = feignUserService.info(userid);
        String userString = FastJsonUtils.toJson(info.get("user"));
        UserEntity user = FastJsonUtils.fromJson(userString, UserEntity.class);
        /**
         * 1.先上传我们的回答
         * 2.更新用户的操作日志
         * */
        if(user!=null){
            AnsEntity ansEntity = new AnsEntity();
            ansEntity.setUserid(userid);
            ansEntity.setQuizid(entity.getQuizid());
            ansEntity.setQuizTitle(entity.getQuizTitle());
            ansEntity.setContext(entity.getContext());
            ansEntity.setUserNickname(user.getNickname());
            /**
             * 进行铭感词过滤, 如果没有出现敏感词那么直接通过
             * 如果超过了阈值直接打回。
             *
             * */
            String context = ansEntity.getContext();
            int count = wordFilter.wordCount(context);
            if(count>=context.length()*threshold){
                return R.error(BizCodeEnum.OVER_SENSITIVE_WORDS.getCode(),BizCodeEnum.OVER_SENSITIVE_WORDS.getMsg());
            }else if(count>0&&count<context.length()*threshold){
                ansEntity.setStatus(1);
                backMessage = "哇！您的内容直接通过了呢！";
            }else {
                String replace = wordFilter.replace(context, '*');
                ansEntity.setContext(replace);
                ansEntity.setStatus(2);
                backMessage="您的回答已提交，正在等待审核哟！";
            }

            /**
             * 获取用户头像
             * */
            R RHeadImg = feignHeadimgService.headimg(userid);
            String headImgString = FastJsonUtils.toJson(RHeadImg.get("headimg"));
            final HeadimgEntity headimg = FastJsonUtils.fromJson(headImgString, HeadimgEntity.class);
            if(headimg!=null){
                ansEntity.setUserImg(headimg.getImgpath());
            }


            ansEntity.setCreatTime(DateUtils.getCurrentTime());
            ansService.save(ansEntity);

            /**
             * 更新用户日志
             * */
            LogAnsEntity logAnsEntity = new LogAnsEntity();
            logAnsEntity.setAction(1);
            logAnsEntity.setQuizid(entity.getQuizid());
            logAnsEntity.setAnsid(ansEntity.getAnsid());
            logAnsEntity.setUserid(userid);
            logAnsEntity.setCreatTime(DateUtils.getCurrentTime());
            feignLogAnsService.save(logAnsEntity);
            /**
             * 设置标志
             * */
            redisUtils.set(RedisTransKey.setBaseUpAnsKey(entity.getUserid())
                    ,1,5, TimeUnit.MINUTES
            );

        }else {
            return R.error(BizCodeEnum.NO_SUCHUSER.getCode(),BizCodeEnum.NO_SUCHUSER.getMsg());
        }
        return R.ok(backMessage);
    }

    @Override
    public R lastQuizAns(GetQuizAnsListEntity entity) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        //组装请求博文列表所需要的数据，当访问的为内部接口时，所有的参数均为Map形式
        params.put("page",entity.getPage().toString());
        params.put("limit",entity.getLimit().toString());
        params.put("accurate","single");
        //此时指定HoleNULL，那么这个key不参与查询
        params.put("table_name","quizid");
        params.put("key",entity.getQuizid().toString());
        params.put("order","desc");
        params.put("status","1");
        PageUtils page = ansService.queryPage(params);
        return R.ok().put("page", page);
    }

    @Override
    public R hotQuiz() throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        //组装请求博文列表所需要的数据，当访问的为内部接口时，所有的参数均为Map形式
        params.put("page","1");
        params.put("limit","10");
        params.put("accurate","many");
        //这里的key是没什么用的单纯占位置的
        params.put("key","1");
        QueryWrapper<QuizEntity> quizEntityQueryWrapper = new QueryWrapper<QuizEntity>();
        quizEntityQueryWrapper.eq("status",1)
                .orderByDesc("quiz_collect_number")
                .orderByDesc("quiz_like_number")
                .orderByDesc("quiz_view_number");

        params.put("accurate_query", SerializeUtil.serialize(quizEntityQueryWrapper));
        PageUtils page = quizService.queryPage(params);
        return R.ok().put("page", page);
    }

    @Override
    public R quizById(Long quizid) {
        QuizEntity quiz = quizService.getById(quizid);
        return R.ok().put("quiz", quiz);
    }
}
