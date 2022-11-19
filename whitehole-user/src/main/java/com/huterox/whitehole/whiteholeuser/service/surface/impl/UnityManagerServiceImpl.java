package com.huterox.whitehole.whiteholeuser.service.surface.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huterox.common.utils.FastJsonUtils;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;
import com.huterox.common.utils.SerializeUtil;
import com.huterox.whitehole.whiteholeuser.dao.HeadimgDao;
import com.huterox.whitehole.whiteholeuser.dao.UserDao;
import com.huterox.whitehole.whiteholeuser.entity.surface.unity.UnityMListEntity;
import com.huterox.whitehole.whiteholeuser.entity.surface.unity.UserVo;
import com.huterox.whitehole.whiteholeuser.exception.BizCodeEnum;
import com.huterox.whitehole.whiteholeuser.service.base.CommunityJoinService;
import com.huterox.whitehole.whiteholeuser.service.base.HeadimgService;
import com.huterox.whitehole.whiteholeuser.service.surface.UnityManagerService;
import com.huterox.whitehole.whiteholeuser.utils.GetIPAddrUtils;
import com.huterox.whitehole.whiteholeuser.utils.RedisTransKey;
import com.huterox.whitehole.whiteholeuser.utils.RedisUtils;
import com.huterox.whiteholecould.entity.blog.BlogEntity;
import com.huterox.whiteholecould.entity.community.CommunityEntity;
import com.huterox.whiteholecould.entity.quiz.AnsEntity;
import com.huterox.whiteholecould.entity.quiz.QuizEntity;
import com.huterox.whiteholecould.entity.user.CommunityJoinEntity;
import com.huterox.whiteholecould.entity.user.HeadimgEntity;
import com.huterox.whiteholecould.entity.user.UserEntity;
import com.huterox.whiteholecould.feign.community.FeignBlogsService;
import com.huterox.whiteholecould.feign.community.FeignCommunityService;
import com.huterox.whiteholecould.feign.quiz.FeignAnsService;
import com.huterox.whiteholecould.feign.quiz.FeignQuizService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UnityManagerServiceImpl implements UnityManagerService {

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    FeignBlogsService feignBlogsService;

    @Autowired
    FeignQuizService feignQuizService;

    @Autowired
    FeignAnsService feignAnsService;

    @Autowired
    FeignCommunityService feignCommunityService;

    @Autowired
    CommunityJoinService communityJoinService;

    @Autowired
    UserDao userDao;

    @Autowired
    HeadimgService headimgService;

    @Autowired
    HeadimgDao headimgDao;

    @Override
    public R AllArticle(UnityMListEntity entity) throws Exception {

        /*
          这里需要进行一个校验,这里的话已经通过了token校验，就没有必要再校验了
         */
        R ok = this.isOk(entity);
        if(ok!=null){
            return ok;
        }
        HashMap<String, Object> params = new HashMap<>();
        params.put("page",entity.getPage());
        params.put("limit",entity.getLimit());
        params.put("accurate","many");
        params.put("key",entity.getUserid());

        QueryWrapper<BlogEntity> blogEntityQueryWrapper = new QueryWrapper<>();
        blogEntityQueryWrapper.eq("communityid",entity.getCommunityid())
                .orderByDesc("blogid");

        params.put("accurate_query", SerializeUtil.serialize(blogEntityQueryWrapper));
        return feignBlogsService.list(params);
    }

    @Override
    public R StatusArticle(UnityMListEntity entity) throws Exception {

        R ok = this.isOk(entity);
        if(ok!=null){
            return ok;
        }
        HashMap<String, Object> params = new HashMap<>();
        params.put("page",entity.getPage());
        params.put("limit",entity.getLimit());
        params.put("accurate","many");
        params.put("key",entity.getUserid());
        QueryWrapper<BlogEntity> blogEntityQueryWrapper = new QueryWrapper<>();
        blogEntityQueryWrapper.eq("communityid",entity.getCommunityid())
                .eq("status",2);
        params.put("accurate_query", SerializeUtil.serialize(blogEntityQueryWrapper));
        return feignBlogsService.list(params);
    }

    @Override
    public R AllQuiz(UnityMListEntity entity) throws Exception {

        R ok = this.isOk(entity);
        if(ok!=null){
            return ok;
        }
        HashMap<String, Object> params = new HashMap<>();
        params.put("page",entity.getPage());
        params.put("limit",entity.getLimit());
        params.put("accurate","many");
        params.put("key",entity.getUserid());

        QueryWrapper<QuizEntity> quizEntityQueryWrapper = new QueryWrapper<>();
        quizEntityQueryWrapper.eq("communityid",entity.getCommunityid())
                .orderByDesc("quizid");
        params.put("accurate_query", SerializeUtil.serialize(quizEntityQueryWrapper));
        return feignQuizService.list(params);
    }

    @Override
    public R StatusQuiz(UnityMListEntity entity) throws Exception {
        /*
         * 这个是社区管理人员审核的
         * */
        R ok = this.isOk(entity);
        if(ok!=null){
            return ok;
        }
        HashMap<String, Object> params = new HashMap<>();
        params.put("page",entity.getPage());
        params.put("limit",entity.getLimit());
        params.put("accurate","many");
        params.put("key",entity.getUserid());

        QueryWrapper<QuizEntity> quizEntityQueryWrapper = new QueryWrapper<>();
        quizEntityQueryWrapper.eq("communityid",entity.getCommunityid())
                .eq("status",2);
        params.put("accurate_query", SerializeUtil.serialize(quizEntityQueryWrapper));
        return feignQuizService.list(params);
    }

    @Override
    public R StatusAns(UnityMListEntity entity) throws Exception {

        R ok = this.isOk(entity);
        if(ok!=null){
            return ok;
        }
        HashMap<String, Object> params = new HashMap<>();
        params.put("page",entity.getPage());
        params.put("limit",entity.getLimit());
        params.put("accurate","many");
        params.put("key",entity.getUserid());

        QueryWrapper<AnsEntity> ansEntityQueryWrapper = new QueryWrapper<>();
        ansEntityQueryWrapper.eq("communityid",entity.getCommunityid())
                .eq("status",2);
        params.put("accurate_query", SerializeUtil.serialize(ansEntityQueryWrapper));
        return feignQuizService.list(params);
    }

    @Override
    public R AllUser(UnityMListEntity entity) throws Exception {
        /*
          先到查到userid
          然后根据userid去查到user
          组装userov类，然后组装分页
          */
        R ok = this.isOk(entity);
        if(ok!=null){
            return ok;
        }
        HashMap<String, Object> params = new HashMap<>();
        params.put("page",String.valueOf(entity.getPage()));
        params.put("limit",String.valueOf(entity.getLimit()));
        params.put("accurate","many");
        params.put("key",entity.getUserid());

        QueryWrapper<CommunityJoinEntity> communityJoinEntityQueryWrapper = new QueryWrapper<>();
        communityJoinEntityQueryWrapper.eq("communityid",entity.getCommunityid())
                .orderByDesc("community_join_time");

        params.put("accurate_query", SerializeUtil.serialize(communityJoinEntityQueryWrapper));
        PageUtils page = communityJoinService.queryPage(params);
        List<?> list = page.getList();
        List<UserVo> resList = new ArrayList<>();
        for(Object o:list){
            CommunityJoinEntity communityJoinEntity = new CommunityJoinEntity();
            BeanUtils.copyProperties(o,communityJoinEntity);
            UserEntity userEntity = userDao.selectById(communityJoinEntity.getUserid());
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(userEntity,userVo);
            //查找对应用户的头像
            HeadimgEntity headimg = headimgService.getOne(
                    new QueryWrapper<HeadimgEntity>().eq("userid",userVo.getUserid())
                            .orderByDesc("imgid")
                            .last("limit 0,1")

            );
            if(headimg!=null){
                userVo.setUserImg(headimg.getImgpath());
            }else {
                userVo.setUserImg(null);
            }
            userVo.setCreatTime(communityJoinEntity.getCommunityJoinTime());
            resList.add(userVo);
        }
        //组装新的PageUtils
        PageUtils userpage = new PageUtils(
                resList,
                page.getTotalCount(),
                page.getPageSize(),
                page.getCurrPage()
        );
        return R.ok().put("page",userpage);
    }

    @Override
    public R StatusUser(UnityMListEntity entity) {
        //这里的话需要到消息服务里面去获取，我们这边没有做存储
        return R.error(-1,"开发中");
    }

    public R isOk(UnityMListEntity entity){
        //返回为null表示通过校验，反之
        String ipAddr = GetIPAddrUtils.GetIPAddr();

        if(redisUtils.hasKey(RedisTransKey.getUserNoSuchAuthority(ipAddr))){
            return R.error(BizCodeEnum.NO_SUCH_COMMUNITY.getCode(),BizCodeEnum.NO_SUCHUSER.getMsg());
        }

        Long communityid = entity.getCommunityid();
        String userid = entity.getUserid();
        R info = feignCommunityService.info(communityid);
        String communityString = FastJsonUtils.toJson(info.get("community"));
        CommunityEntity communityEntity = FastJsonUtils.fromJson(communityString, CommunityEntity.class);
        if(communityEntity==null){
            return R.error(BizCodeEnum.NO_SUCH_COMMUNITY.getCode(),BizCodeEnum.NO_SUCHUSER.getMsg());
        }
        if(!communityEntity.getUserid().equals(userid)){
            redisUtils.set(RedisTransKey.setUserNoSuchAuthority(ipAddr),1,10, TimeUnit.MINUTES);
            return R.error(BizCodeEnum.NO_SUCH_AUTHORITY.getCode(),BizCodeEnum.NO_SUCH_AUTHORITY.getMsg());
        }
        return null;
    }
}
