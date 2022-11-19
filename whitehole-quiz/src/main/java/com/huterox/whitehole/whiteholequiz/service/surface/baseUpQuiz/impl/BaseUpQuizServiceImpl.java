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
import com.huterox.whitehole.whiteholequiz.utils.GetIPAddrUtils;
import com.huterox.whitehole.whiteholequiz.utils.RedisTransKey;
import com.huterox.whitehole.whiteholequiz.utils.RedisUtils;
import com.huterox.whiteholecould.entity.community.CommunityEntity;
import com.huterox.whiteholecould.entity.community.Q.AuthenticationAnsQ;
import com.huterox.whiteholecould.entity.community.QuizsEntity;
import com.huterox.whiteholecould.entity.message.Q.HoleAduitMsgQ;
import com.huterox.whiteholecould.entity.quiz.AnsEntity;
import com.huterox.whiteholecould.entity.quiz.Q.CommunityUpAnsQ;
import com.huterox.whiteholecould.entity.quiz.Q.CommunityUpQuizQ;
import com.huterox.whiteholecould.entity.quiz.QuizEntity;
import com.huterox.whiteholecould.entity.user.HeadimgEntity;
import com.huterox.whiteholecould.entity.user.LogAnsEntity;
import com.huterox.whiteholecould.entity.user.LogQuizEntity;
import com.huterox.whiteholecould.entity.user.UserEntity;
import com.huterox.whiteholecould.feign.community.FeignAuthenticationAnsService;
import com.huterox.whiteholecould.feign.community.FeignQuizsService;
import com.huterox.whiteholecould.feign.message.FeignHoleAduitMsgService;
import com.huterox.whiteholecould.feign.user.*;
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

    @Autowired
    FeignQuizsService feignQuizsService;

    @Autowired
    FeignHoleAduitMsgService feignHoleAduitMsgService;

    @Autowired
    FeignAuthenticationAnsService feignAuthenticationAnsService;

    private final static Double threshold = 0.05;

    @Override
    public R BaseUpQuiz(UpQuizEntity entity) {
        String userid = entity.getUserid();
        if(redisUtils.hasKey(RedisTransKey.getBaseUpQuizKey(entity.getUserid()))){
            return R.error(BizCodeEnum.HAS_UPQUIZ.getCode(), BizCodeEnum.HAS_UPQUIZ.getMsg());
        }
        //判断用户是否存在
        String backMessage;
        R info = feignUserService.info(userid);
        String userString = FastJsonUtils.toJson(info.get("user"));
        UserEntity user = FastJsonUtils.fromJson(userString, UserEntity.class);
        if(user!=null){
            //这里的话我们需要开启分布式事务，但是现在目前我们没有去做集成
            QuizEntity quizEntity = new QuizEntity();
            BeanUtils.copyProperties(entity,quizEntity);
            quizEntity.setCreateTime(DateUtils.getCurrentTime());
            quizEntity.setUserNickname(user.getNickname());
            /*
             * 查看用户的头像，这里的话，我们就不去同步了
             * 我们这里用户是谁压根不重要，里面的内容是啥才重要
             * */


            R RHeadImg = feignHeadimgService.headimg(userid);
            String headImgString = FastJsonUtils.toJson(RHeadImg.get("headimg"));
            final HeadimgEntity headimg = FastJsonUtils.fromJson(headImgString, HeadimgEntity.class);
            if(headimg!=null){
                quizEntity.setUserImg(headimg.getImgpath());
            }

            /*
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
                /*
                 * 这个时候的话就是需要审核的了
                 * 同时需要把对应的铭感内容进行替换
                 */
                String replace = wordFilter.replace(quizContent, '*');
                quizEntity.setQuizContent(replace);
                quizEntity.setStatus(2);
                backMessage="您的提问已提交，正在等待审核哟！";
            }
            //不管前端传过来的是不是-1，最后都是-1
            quizEntity.setCommunityid(-1L);
            quizService.save(quizEntity);

            /*
             * 更新用户的操作日志
             * */
            LogQuizEntity logQuizEntity = new LogQuizEntity();
            logQuizEntity.setQuizid(quizEntity.getQuizid());
            logQuizEntity.setQuizTitle(quizEntity.getQuizTitle());
            logQuizEntity.setCreatTime(quizEntity.getCreateTime());
            logQuizEntity.setUserid(quizEntity.getUserid());
            logQuizEntity.setAction(1);
            feignLogQuizService.save(logQuizEntity);
            /*
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
    public R CommunityUpQuiz(CommunityUpQuizQ communityUpQuizQ) {
        /*
         * 这个是在社区上传提问用的接口
         * */
        String userid = communityUpQuizQ.getUserid();
        R info = feignUserService.info(userid);
        int status;
        String userString = FastJsonUtils.toJson(info.get("user"));
        UserEntity user = FastJsonUtils.fromJson(userString, UserEntity.class);
        if(user!=null){
            //这里的话我们需要开启分布式事务，但是现在目前我们没有去做集成

            QuizEntity quizEntity = new QuizEntity();
            BeanUtils.copyProperties(communityUpQuizQ,quizEntity);
            quizEntity.setCreateTime(DateUtils.getCurrentTime());
            quizEntity.setUserNickname(user.getNickname());
            /*
             * 查看用户的头像，这里的话，我们就不去同步了
             * 我们这里用户是谁压根不重要，里面的内容是啥才重要
             * */

            R RHeadImg = feignHeadimgService.headimg(userid);
            String headImgString = FastJsonUtils.toJson(RHeadImg.get("headimg"));
            final HeadimgEntity headimg = FastJsonUtils.fromJson(headImgString, HeadimgEntity.class);
            if(headimg!=null){
                quizEntity.setUserImg(headimg.getImgpath());
            }

            /*
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
                status = 1;
            }else {
                /*
                 * 这个时候的话就是需要审核的了
                 * 同时需要把对应的铭感内容进行替换
                 */
                String replace = wordFilter.replace(quizContent, '*');
                quizEntity.setQuizContent(replace);
                quizEntity.setStatus(2);
                status = 2;
            }
            quizService.save(quizEntity);

            /*
             * 更新用户的操作日志
             * */
            LogQuizEntity logQuizEntity = new LogQuizEntity();
            logQuizEntity.setQuizid(quizEntity.getQuizid());
            logQuizEntity.setQuizTitle(quizEntity.getQuizTitle());
            logQuizEntity.setCreatTime(quizEntity.getCreateTime());
            logQuizEntity.setUserid(quizEntity.getUserid());
            logQuizEntity.setAction(1);
            feignLogQuizService.save(logQuizEntity);
            /*
            * 在这里更新社区的提问的情况
            * */
            QuizsEntity quizsEntity = new QuizsEntity();
            BeanUtils.copyProperties(quizEntity,quizsEntity);
            feignQuizsService.save(quizsEntity);
            /*
             * 发送消息
             * */
            if (status == 1) {
                /*
                 * 此时是直接通过了审核，那么直接进行发送
                 * 如果没有的话，那么就是后台通过审核由MQ发送消息
                 * */
                HoleAduitMsgQ holeAduitMsgQ = new HoleAduitMsgQ();
                holeAduitMsgQ.setMsg(
                        "您的博文" + quizEntity.getQuizTitle() + "通过了系统审核，成功发布在了"
                                +communityUpQuizQ.getCommunityName()+"社区中"
                );
                holeAduitMsgQ.setMsgtitle("提问审核通过");
                holeAduitMsgQ.setUserid(user.getUserid());
                holeAduitMsgQ.setLinkid(String.valueOf(quizEntity.getQuizid()));
                holeAduitMsgQ.setType(2);
                feignHoleAduitMsgService.holeAduitMsg(holeAduitMsgQ);

                //同时向管理员发送博文通过了系统审核
                holeAduitMsgQ.setMsg(
                        user.getUsername()+"在"+communityUpQuizQ.getCommunityName()+"发布了问答" +
                                quizEntity.getQuizTitle()+
                                "已通过系统审核"
                );
                holeAduitMsgQ.setMsgtitle("社区博文上传");
                holeAduitMsgQ.setUserid(user.getUserid());
                holeAduitMsgQ.setLinkid(String.valueOf(quizEntity.getQuizid()));
                holeAduitMsgQ.setType(1);
                feignHoleAduitMsgService.holeAduitMsg(holeAduitMsgQ);
            } else {
                /*
                 * 未通过审核，需要管理员进行处理
                 * */
                HoleAduitMsgQ holeAduitMsgQ = new HoleAduitMsgQ();
                holeAduitMsgQ.setMsg(
                        user.getUsername()+"在"+communityUpQuizQ.getCommunityName()+"提交博文" +
                                "未通过系统审核需进行复审"
                );
                holeAduitMsgQ.setMsgtitle(communityUpQuizQ.getCommunityName()+"提问审核通知");
                holeAduitMsgQ.setUserid(communityUpQuizQ.getCommunityUserid());
                holeAduitMsgQ.setLinkid(String.valueOf(quizEntity.getQuizid()));
                holeAduitMsgQ.setType(2);
                feignHoleAduitMsgService.holeAduitMsg(holeAduitMsgQ);
            }

        }else {
            //-2表示没有找到这个用户，用户校验都没通过
            return R.ok().put("status",-2);
        }
        return R.ok().put("status",status);
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
        /*
         * 负责上传用户的回答
         * */
        int AnsStatus;
        String userid = entity.getUserid();
        String backMessage;
        //判断用户是否过于频繁上传回答
        if(redisUtils.hasKey(RedisTransKey.getBaseUpQuizKey(entity.getUserid()))){
            return R.error(BizCodeEnum.HAS_UPANS.getCode(), BizCodeEnum.HAS_UPANS.getMsg());
        }
        String ipAddr = GetIPAddrUtils.GetIPAddr();
        if(redisUtils.hasKey(RedisTransKey.getRedisAuthorityBAD(ipAddr))){
            return R.error(BizCodeEnum.BAD_AUTHORITY.getCode(),BizCodeEnum.BAD_AUTHORITY.getMsg());
        }

        R info = feignUserService.info(userid);
        String userString = FastJsonUtils.toJson(info.get("user"));
        UserEntity user = FastJsonUtils.fromJson(userString, UserEntity.class);
        /*
         * 1.先上传我们的回答
         * 2.更新用户的操作日志
         * */
        if(user==null){
            return R.error(BizCodeEnum.NO_SUCHUSER.getCode(),BizCodeEnum.NO_SUCHUSER.getMsg());
        }

        /*
         * 当验证ok用户之后的话，我们接下来还需要去验证一下
         * 问题是不是真的是这个并且存在
         * */

        Long quizid = entity.getQuizid();
        QuizEntity byId = quizService.getById(quizid);
        if(byId==null){
            return R.error(BizCodeEnum.NO_SUCHQUIZ.getCode(),BizCodeEnum.NO_SUCHQUIZ.getMsg());
        }

        /*
         * 这里的话先检测这个社区有没有对到
         * （默认没有上传的社区，也就是根社区的id为-1）
         * */
        Long communityid = entity.getCommunityid();
        if(communityid!=-1 && !byId.getCommunityid().equals(entity.getCommunityid())){
            redisUtils.set(RedisTransKey.setRedisAuthorityBAD(ipAddr)
                    ,1,10, TimeUnit.MINUTES
            );
            return R.error(BizCodeEnum.BAD_AUTHORITY.getCode(),BizCodeEnum.BAD_AUTHORITY.getMsg());
        }

        AnsEntity ansEntity = new AnsEntity();
        ansEntity.setUserid(userid);
        ansEntity.setQuizid(entity.getQuizid());
        ansEntity.setQuizTitle(entity.getQuizTitle());
        ansEntity.setContext(entity.getContext());
        ansEntity.setUserNickname(user.getNickname());
        /*
         * 进行铭感词过滤, 如果没有出现敏感词那么直接通过
         * 如果超过了阈值直接打回。
         * */
        String context = ansEntity.getContext();
        int count = wordFilter.wordCount(context);
        if(count>=context.length()*threshold){
            return R.error(BizCodeEnum.OVER_SENSITIVE_WORDS.getCode(),BizCodeEnum.OVER_SENSITIVE_WORDS.getMsg());
        }else if(count>0 && count<context.length()*threshold){
            AnsStatus = 1;
            ansEntity.setStatus(AnsStatus);
            backMessage = "哇！您的内容直接通过了呢！";
        }else {
            String replace = wordFilter.replace(context, '*');
            ansEntity.setContext(replace);
            AnsStatus = 2;
            ansEntity.setStatus(AnsStatus);
            backMessage="您的回答已提交，正在等待审核哟！";
        }


         /*
         * 此时的话兵分两路
         *  1. 当communityid = -1 时，按照原来的逻辑进行
         *  2. 当communityid != -1 时，在基础上，通知社区的管理人员
         * */

        CommunityEntity community = null;
        if(communityid!=-1){
            //此时进行一个验证,当验证都通过之后的话，就进入下一个阶段的工作
            CommunityUpAnsQ communityUpAnsQ = new CommunityUpAnsQ();
            BeanUtils.copyProperties(entity,communityUpAnsQ);
            R r = this.CommunityUpAns(communityUpAnsQ);
            int status = Integer.parseInt(String.valueOf(r.get("status")));
            if(status==-1){
                backMessage="当前社区不存在！";
                return R.ok(backMessage);
            }else{
                String communityString = FastJsonUtils.toJson(r.get("community"));
                community = FastJsonUtils.fromJson(communityString, CommunityEntity.class);
                if(status==1){
                    backMessage="请先加入"+community.getCommunityTitle()+"社区呦~";
                    return R.ok(backMessage);
                }
            }
        }

        /*
         * 这部分是开始完成一个对应的Ans的存储了
         * */
        R RHeadImg = feignHeadimgService.headimg(userid);
        String headImgString = FastJsonUtils.toJson(RHeadImg.get("headimg"));
        HeadimgEntity headimg = FastJsonUtils.fromJson(headImgString, HeadimgEntity.class);
        if(headimg!=null){
            ansEntity.setUserImg(headimg.getImgpath());
        }

        ansEntity.setCreatTime(DateUtils.getCurrentTime());
        ansEntity.setCommunityid(communityid);
        ansService.save(ansEntity);

        /*
         * 更新用户日志
         * */
        LogAnsEntity logAnsEntity = new LogAnsEntity();
        logAnsEntity.setAction(1);
        logAnsEntity.setQuizid(entity.getQuizid());
        logAnsEntity.setAnsid(ansEntity.getAnsid());
        logAnsEntity.setUserid(userid);
        logAnsEntity.setCreatTime(DateUtils.getCurrentTime());
        feignLogAnsService.save(logAnsEntity);
        /*
         * 发送消息
         * */
        if(communityid!=-1){
            if (AnsStatus == 1) {
                /*
                 * 此时是直接通过了审核，那么直接进行发送
                 * 如果没有的话，那么就是后台通过审核由MQ发送消息
                 * */
                HoleAduitMsgQ holeAduitMsgQ = new HoleAduitMsgQ();
                holeAduitMsgQ.setMsg(
                        "您对" + byId.getQuizTitle() + "提问的回答已成功发布在"
                                +community.getCommunityTitle()+"社区中"
                );
                holeAduitMsgQ.setMsgtitle("回答审核通过");
                holeAduitMsgQ.setUserid(user.getUserid());
                holeAduitMsgQ.setLinkid(String.valueOf(ansEntity.getAnsid()));
                holeAduitMsgQ.setType(3);
                feignHoleAduitMsgService.holeAduitMsg(holeAduitMsgQ);

                //同时向管理员发送回答通过了系统审核
                holeAduitMsgQ.setMsg(
                        user.getUsername()+"在"+community.getCommunityTitle()+"发布了问答对" +
                                byId.getQuizTitle()+
                                "已通过系统审核"
                );
                holeAduitMsgQ.setMsgtitle("社区回答上传");
                //这个是给对应的社区的管理员的
                holeAduitMsgQ.setUserid(community.getUserid());
                holeAduitMsgQ.setLinkid(String.valueOf(ansEntity.getAnsid()));
                holeAduitMsgQ.setType(3);
                feignHoleAduitMsgService.holeAduitMsg(holeAduitMsgQ);
            } else {
                /*
                 * 为通过审核，需要管理员进行处理
                 * */
                HoleAduitMsgQ holeAduitMsgQ = new HoleAduitMsgQ();
                holeAduitMsgQ.setMsg(
                        user.getUsername()+"在"+community.getCommunityTitle()+"发布了问答对" +
                                byId.getQuizTitle()+
                                "未通过系统审核需进行复审"
                );
                holeAduitMsgQ.setMsgtitle("社区回答上传");
                //这个是给对应的社区的管理员的
                holeAduitMsgQ.setUserid(community.getUserid());
                holeAduitMsgQ.setLinkid(String.valueOf(ansEntity.getAnsid()));
                holeAduitMsgQ.setType(3);
                feignHoleAduitMsgService.holeAduitMsg(holeAduitMsgQ);
            }

        }

        /*
         * 设置标志
         * */
        redisUtils.set(RedisTransKey.setBaseUpAnsKey(entity.getUserid())
                ,1,5, TimeUnit.MINUTES
        );

        return R.ok(backMessage);
    }

    @Override
    public R CommunityUpAns(CommunityUpAnsQ communityUpAnsQ) {
        /*
         * 由于Ans这个接口比较特殊，只有一个，因此这一个接口需要完成两个功能
         * 1. 当确定问题Ans属于一个社区的时候，去对该社区进行验证，验证通过之后的话，按照流程进行操作
         *  -1表示社区不存在，0表示正常，1表示当前的用户没有这个权限（不属于这个社区或者，不是管理员）
         *  当正常的时候，将获得community对象
         * */
        AuthenticationAnsQ authenticationAnsQ = new AuthenticationAnsQ();
        BeanUtils.copyProperties(communityUpAnsQ,authenticationAnsQ);
        return feignAuthenticationAnsService.AuthenticationAnsQ(authenticationAnsQ);
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
        QueryWrapper<QuizEntity> quizEntityQueryWrapper = new QueryWrapper<>();
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
