package com.huterox.whitehole.whiteholeuser.service.surface.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huterox.common.utils.*;
import com.huterox.whitehole.whiteholeuser.entity.surface.userspace.*;
import com.huterox.whitehole.whiteholeuser.exception.BizCodeEnum;
import com.huterox.whitehole.whiteholeuser.service.base.*;
import com.huterox.whitehole.whiteholeuser.service.surface.MailService;
import com.huterox.whitehole.whiteholeuser.service.surface.UserSpaceService;
import com.huterox.whitehole.whiteholeuser.utils.*;
import com.huterox.whiteholecould.entity.blog.BlogEntity;
import com.huterox.whiteholecould.entity.community.CommunityEntity;
import com.huterox.whiteholecould.entity.quiz.AnsEntity;
import com.huterox.whiteholecould.entity.quiz.QuizEntity;
import com.huterox.whiteholecould.entity.user.HeadimgEntity;
import com.huterox.whiteholecould.entity.user.UserEntity;
import com.huterox.whiteholecould.feign.blog.FeignBlogService;
import com.huterox.whiteholecould.feign.community.FeignCommunityService;
import com.huterox.whiteholecould.feign.quiz.FeignAnsService;
import com.huterox.whiteholecould.feign.quiz.FeignQuizService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Service
public class UserSpaceServiceImpl implements UserSpaceService {

    @Autowired
    FeignBlogService feignBlogService;

    @Autowired
    BlogForkService blogForkService;

    @Autowired
    UserService userService;

    @Autowired
    HeadimgService headimgService;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    MailService mailService;

    @Autowired
    FeignQuizService feignQuizService;

    @Autowired
    FeignAnsService feignAnsService;

    @Autowired
    CommunityJoinService communityJoinService;

    @Autowired
    ManageCommunityService manageCommunityService;

    @Autowired
    FeignCommunityService feignCommunityService;


    @Override
    public R userShowInfo(String userid) {
        UserEntity User = userService.getOne(
                new QueryWrapper<UserEntity>().eq("userid", userid)
        );
        if(User!=null){
            UserShowInfoEntity userShowInfoEntity = new UserShowInfoEntity();
            BeanUtils.copyProperties(User,userShowInfoEntity);
            //???????????????????????????
            HeadimgEntity headimg = headimgService.getOne(
                    new QueryWrapper<HeadimgEntity>().eq("userid", userid)
                            .orderByDesc("imgid")
                            .last("limit 0,1")

            );
            if(headimg!=null){
                userShowInfoEntity.setUserpic(headimg.getImgpath());
            }else {
                userShowInfoEntity.setUserpic(null);
            }
            String ipAddr = GetIPAddrUtils.GetIPAddr();
            userShowInfoEntity.setIpAddr(ipAddr);
//            ????????????BizCodeEnum.SUCCESSFUL.getMsg()??????????????????
            return R.ok(BizCodeEnum.SUCCESSFUL.getMsg())
                    .put("User",userShowInfoEntity);
        }else {
            return R.error(BizCodeEnum.NO_SUCHUSER.getCode(),BizCodeEnum.NO_SUCHUSER.getMsg());
        }
    }


    @Override
    public R userIsLogin(String userid) {
        return R.ok(BizCodeEnum.SUCCESSFUL.getMsg());
    }

    @Override
    public R userShowInfoEditor(String userid) {
        UserEntity User = userService.getOne(
                new QueryWrapper<UserEntity>().eq("userid", userid)
        );
        if(User!=null){
            UserInfoEditEntity userInfoEditEntity = new UserInfoEditEntity();
            BeanUtils.copyProperties(User,userInfoEditEntity);
//            ????????????BizCodeEnum.SUCCESSFUL.getMsg()??????????????????
            return R.ok(BizCodeEnum.SUCCESSFUL.getMsg())
                    .put("User",userInfoEditEntity);
        }else {
            return R.error(BizCodeEnum.NO_SUCHUSER.getCode(),BizCodeEnum.NO_SUCHUSER.getMsg());
        }
    }

    @Override
    public R userInfoEditor(UserInfoEditEntity userInfoEditEntity) {
        //??????????????????????????????
        if(redisUtils.hasKey(RedisTransKey.getUserInfoEditorKey(userInfoEditEntity.getUserid()))){
            return R.error(BizCodeEnum.OVER_REQUESTS.getCode(), BizCodeEnum.OVER_REQUESTS.getMsg());
        }
        UserEntity User = userService.getOne(
                new QueryWrapper<UserEntity>().eq("userid", userInfoEditEntity.getUserid())
        );
        if(User!=null){
            //??????????????????????????????????????????????????????
            BeanUtils.copyProperties(userInfoEditEntity,User);
            userService.updateById(User);
//            System.out.println(User);
            redisUtils.set(RedisTransKey.setUserInfoEditorKey(userInfoEditEntity.getUserid())
                    ,1,1, TimeUnit.DAYS
            );
            return R.ok(BizCodeEnum.SUCCESSFUL.getMsg());
        }else {
            return R.error(BizCodeEnum.NO_SUCHUSER.getCode(),BizCodeEnum.NO_SUCHUSER.getMsg());
        }
    }

    @Override
    public R userUpImg(UserHandImgEntity entity) {
        if(redisUtils.hasKey(RedisTransKey.getUserUpHeadImgKey(entity.getUserid()))){
            return R.error(BizCodeEnum.OVER_REQUESTS.getCode(), BizCodeEnum.OVER_REQUESTS.getMsg());
        }
        UserEntity User = userService.getOne(
                new QueryWrapper<UserEntity>().eq("userid", entity.getUserid())
        );
        if(User!=null){
            HeadimgEntity headimgEntity = new HeadimgEntity();
            BeanUtils.copyProperties(entity,headimgEntity);
            headimgEntity.setCreatTime(DateUtils.getCurrentTime());
            headimgService.save(headimgEntity);
            redisUtils.set(RedisTransKey.setUserUpHeadImgKey(entity.getUserid())
                    ,1,1, TimeUnit.DAYS
            );
            return R.ok(BizCodeEnum.SUCCESSFUL.getMsg());
        }else {
            return R.error(BizCodeEnum.NO_SUCHUSER.getCode(),BizCodeEnum.NO_SUCHUSER.getMsg());
        }
    }

    @Override
    public R emailCode(UserSpaceEmailCodeEntity entity) {
        //??????10???????????????
        String userid = entity.getUserid();
        String password = entity.getPassword();
        if (redisUtils.hasKey(RedisTransKey.getUserChangePassWordEmailKey(userid))) {
            return R.error(BizCodeEnum.OVER_EMAIL_REQUESTS.getCode(), BizCodeEnum.OVER_EMAIL_REQUESTS.getMsg());
        }
        UserEntity User = userService.getOne(
                new QueryWrapper<UserEntity>().eq("userid", userid)
        );
        if (User != null) {
            //??????????????????
            String emailCode = CodeUtils.creatCode(6);
            String email = User.getEmail();
            //???????????????,10???????????????
            UserSpaceEmailCodeSaveEntity userSpaceEmailCodeSaveEntity = new UserSpaceEmailCodeSaveEntity(emailCode,password);
            redisUtils.set(RedisTransKey.setUserChangePassWordEmailKey(userid), userSpaceEmailCodeSaveEntity,
                    10, TimeUnit.MINUTES
            );
            //?????????????????????
            mailService.sendCodeMailMessage(email,emailCode);
        } else {
            return R.error(BizCodeEnum.NO_SUCHUSER.getCode(),BizCodeEnum.NO_SUCHUSER.getMsg());
        }
        return R.ok(BizCodeEnum.SUCCESSFUL.getMsg());
    }
    @Override
    public R userChangePassword(UserSpaceChangePasswordEntity entity) {
        String userid = entity.getUserid();
        String emailCode = entity.getEmailCode();
        UserEntity User = userService.getOne(
                new QueryWrapper<UserEntity>().eq("userid", userid)
        );
        if(User!=null){
            if(redisUtils.hasKey(RedisTransKey.getUserChangePassWordEmailKey(userid))){
                Object o = redisUtils.get(RedisTransKey.getUserChangePassWordEmailKey(userid));
                UserSpaceEmailCodeSaveEntity userSpaceEmailCodeSaveEntity = JSON.parseObject(o.toString(), UserSpaceEmailCodeSaveEntity.class);
                if(emailCode.equals(userSpaceEmailCodeSaveEntity.getEmailCode())){
                    //?????????????????????????????????
                    if(entity.getPassword().equals(userSpaceEmailCodeSaveEntity.getPassword())){
                        BeanUtils.copyProperties(userSpaceEmailCodeSaveEntity,User);
                        redisUtils.set(RedisTransKey.setUserInfoEditorKey(userid)
                                ,1,1, TimeUnit.DAYS
                        );
                        return R.ok(BizCodeEnum.SUCCESSFUL.getMsg());
                    }else {
                        return R.error(BizCodeEnum.PASSWORD_NO_EQUAL.getCode(),BizCodeEnum.PASSWORD_NO_EQUAL.getMsg());
                    }
                }
            }else {
                //????????????????????????????????????????????????
                return R.error(BizCodeEnum.DON_NOT_SENDEMAIL.getCode(),BizCodeEnum.DON_NOT_SENDEMAIL.getMsg());
            }
        }else {
            return R.error(BizCodeEnum.NO_SUCHUSER.getCode(),BizCodeEnum.NO_SUCHUSER.getMsg());
        }
        return null;
    }

    @Override
    public R userAllArticle(UserSpaceInfoListQueryEntity entity) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        //???????????????????????????????????????????????????????????????????????????????????????????????????Map??????
        params.put("page",entity.getPage());
        params.put("limit",entity.getLimit());
        params.put("accurate","many");
        //????????????key????????????????????????????????????????????????????????????????????????????????????????????????
        //??????????????????????????????
        params.put("key",entity.getUserid());
        QueryWrapper<BlogEntity> blogEntityQueryWrapper = new QueryWrapper<BlogEntity>();
        blogEntityQueryWrapper.eq("userid",entity.getUserid())
                        .orderByDesc("blogid");

        params.put("accurate_query", SerializeUtil.serialize(blogEntityQueryWrapper));
        //??????????????????
        return feignBlogService.list(params);
    }

    @Override
    public R userStatusArticle(UserSpaceInfoListQueryEntity entity) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        //???????????????????????????????????????????????????????????????????????????????????????????????????Map??????
        params.put("page",entity.getPage());
        params.put("limit",entity.getLimit());
        params.put("accurate","many");
        params.put("key",entity.getUserid());
        QueryWrapper<BlogEntity> blogEntityQueryWrapper = new QueryWrapper<BlogEntity>();
        blogEntityQueryWrapper.eq("userid",entity.getUserid())
                .ne("status",1)
                .orderByDesc("blogid");

        params.put("accurate_query", SerializeUtil.serialize(blogEntityQueryWrapper));
        //??????????????????
        return feignBlogService.list(params);
    }

    @Override
    public R userPrivateArticle(UserSpaceInfoListQueryEntity entity) {
        HashMap<String, Object> params = new HashMap<>();
        //???????????????????????????????????????????????????????????????????????????????????????????????????Map??????
        params.put("page",entity.getPage());
        params.put("limit",entity.getLimit());
        params.put("accurate","single");
        params.put("table_name","userid");
        params.put("order","desc");
        params.put("status",1);
        params.put("level",2);
        params.put("key",entity.getUserid());
        //??????????????????
        return feignBlogService.list(params);
    }

    @Override
    public R userForkArticle(UserSpaceInfoListQueryEntity entity) {
//        ??????????????????????????????????????????
        HashMap<String, Object> params = new HashMap<>();
        params.put("page",entity.getPage().toString());
        params.put("limit",entity.getLimit().toString());
        params.put("key",entity.getUserid());
        params.put("accurate","single");
        params.put("order","desc");
        params.put("table_name","userid");
        PageUtils page = blogForkService.queryPage(params);
        return R.ok().put("page", page);
    }

    @Override
    public R userAllQuiz(UserSpaceInfoListQueryEntity entity) throws Exception {
        //???????????????????????????
        HashMap<String, Object> params = new HashMap<>();
        //???????????????????????????????????????????????????????????????????????????????????????????????????Map??????
        params.put("page",entity.getPage());
        params.put("limit",entity.getLimit());
        params.put("accurate","many");
        params.put("key",entity.getUserid());
        QueryWrapper<QuizEntity> quizEntityQueryWrapper = new QueryWrapper<QuizEntity>();
        quizEntityQueryWrapper.eq("userid",entity.getUserid())
                .orderByDesc("quizid");

        params.put("accurate_query", SerializeUtil.serialize(quizEntityQueryWrapper));
        return feignQuizService.list(params);
    }

    @Override
    public R userAllAns(UserSpaceInfoListQueryEntity entity) throws Exception {
        //???????????????????????????
        HashMap<String, Object> params = new HashMap<>();
        //???????????????????????????????????????????????????????????????????????????????????????????????????Map??????
        params.put("page",entity.getPage());
        params.put("limit",entity.getLimit());
        params.put("accurate","many");
        params.put("key",entity.getUserid());
        QueryWrapper<AnsEntity> ansEntityQueryWrapper = new QueryWrapper<AnsEntity>();
        ansEntityQueryWrapper.eq("userid",entity.getUserid())
                .orderByDesc("ansid");
        params.put("accurate_query", SerializeUtil.serialize(ansEntityQueryWrapper));
        return feignAnsService.list(params);
    }

    @Override
    public R userJoinUnity(UserSpaceInfoListQueryEntity entity) throws Exception {
        //?????????????????????
        HashMap<String, Object> params = new HashMap<>();
        params.put("page",entity.getPage().toString());
        params.put("limit",entity.getLimit().toString());
        params.put("key",entity.getUserid());
        params.put("accurate","single");
        params.put("order","desc");
        params.put("table_name","userid");
        PageUtils page = communityJoinService.queryPage(params);
        return R.ok().put("page", page);
    }

    @Override
    public R userManagerUnity(UserSpaceInfoListQueryEntity entity) throws Exception {
        //?????????????????????
        HashMap<String, Object> params = new HashMap<>();
        params.put("page",entity.getPage().toString());
        params.put("limit",entity.getLimit().toString());
        params.put("key",entity.getUserid());
        params.put("accurate","many");
        QueryWrapper<CommunityEntity> communityEntityQueryWrapper = new QueryWrapper<>();
        communityEntityQueryWrapper.eq("userid",entity.getUserid());
        params.put("accurate_query",SerializeUtil.serialize(communityEntityQueryWrapper));
        return feignCommunityService.list(params);
    }

}
