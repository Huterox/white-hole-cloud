package com.huterox.whitehole.whiteholecommunity.service.surface.impl;

import com.huterox.common.utils.DateUtils;
import com.huterox.common.utils.FastJsonUtils;
import com.huterox.common.utils.R;
import com.huterox.common.wordfilter.WordFilter;
import com.huterox.whitehole.whiteholecommunity.entity.surface.communityUp.Q.UpBlogQ;
import com.huterox.whitehole.whiteholecommunity.entity.surface.communityUp.Q.UpCommunityQ;
import com.huterox.whitehole.whiteholecommunity.entity.surface.communityUp.Q.UpQuizQ;
import com.huterox.whitehole.whiteholecommunity.exception.BizCodeEnum;
import com.huterox.whitehole.whiteholecommunity.service.base.CommunityService;
import com.huterox.whitehole.whiteholecommunity.service.surface.CommunityUpService;
import com.huterox.whitehole.whiteholecommunity.utils.RedisTransKey;
import com.huterox.whitehole.whiteholecommunity.utils.RedisUtils;
import com.huterox.whiteholecould.entity.blog.Q.CommunityBlogUpQ;
import com.huterox.whiteholecould.entity.community.CommunityEntity;
import com.huterox.whiteholecould.entity.community.Q.AuthenticationAnsQ;
import com.huterox.whiteholecould.entity.quiz.Q.CommunityUpQuizQ;
import com.huterox.whiteholecould.entity.user.*;
import com.huterox.whiteholecould.feign.blog.FeignCommunityBlogUp;
import com.huterox.whiteholecould.feign.quiz.FeignCommunityUpQuizService;
import com.huterox.whiteholecould.feign.user.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class CommunityUpServiceImpl implements CommunityUpService {

    @Autowired
    FeignUserService feignUserService;

    @Autowired
    FeignHeadimgService feignHeadimgService;

    @Autowired
    WordFilter wordFilter;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    CommunityService communityService;

    @Autowired
    FeignManageCommunityService feignManageCommunityService;

    @Autowired
    FeignLogManageCommunityService feignLogManageCommunityService;

    @Autowired
    FeignCommunityisInService feignCommunityisInService;

    @Autowired
    FeignCommunityBlogUp feignCommunityBlogUp;

    @Autowired
    FeignCommunityUpQuizService feignCommunityUpQuizService;

    private final static Double threshold = 0.05;
    @Override
    public R upCommunity(UpCommunityQ upCommunityQ) {
        /*
         * 1.获取用户信息，进行校验，进行审核
         * 2.获取用户头像
         * 3.完成存储
         * */

        String userid = upCommunityQ.getUserid();
        if(redisUtils.hasKey(RedisTransKey.getUpCommunityKey(userid))){
            return R.error(BizCodeEnum.OVER_UPCOMMUNITY.getCode(), BizCodeEnum.OVER_UPCOMMUNITY.getMsg());
        }

        R info = feignUserService.info(userid);
        String userString = FastJsonUtils.toJson(info.get("user"));
        UserEntity user = FastJsonUtils.fromJson(userString, UserEntity.class);
        if(user!=null){
            /*
             * 在我们的这边的的话也是需要对这个进行审核的，但是审核的方式较为简单
             * 只是看这个描述和社区名称进行审核。
             * */

            String desc = upCommunityQ.getDesc();
            String name = upCommunityQ.getName();
            boolean isPass = true;
            int countName = wordFilter.wordCount(name);
            if(countName>name.length()*threshold){
                //没有通过，10分钟之后才可以再次提交
                isPass = false;
            }else {
                int countDesc = wordFilter.wordCount(desc);
                if(countDesc>desc.length()*threshold){
                    isPass=false;
                }
            }
            if(!isPass){
                //没有通过，直接停止
                redisUtils.set(RedisTransKey.setUpCommunityKey(userid)
                        ,1,10, TimeUnit.MINUTES
                );
                return R.error(BizCodeEnum.BAD_COMMUNITY_UP.getCode(),BizCodeEnum.BAD_COMMUNITY_UP.getMsg());
            }
            //此时正式开始业务

            CommunityEntity communityEntity = new CommunityEntity();

            //查询用户的头像信息
            R RHeadImg = feignHeadimgService.headimg(userid);
            String headImgString = FastJsonUtils.toJson(RHeadImg.get("headimg"));
            HeadimgEntity headimg = FastJsonUtils.fromJson(headImgString, HeadimgEntity.class);
            if(headimg!=null){
                communityEntity.setUserimg(headimg.getImgpath());
            }
            //开始组装信息
            communityEntity.setCommunityImg(upCommunityQ.getUrl());
            communityEntity.setCommunityInfo(upCommunityQ.getDesc());
            communityEntity.setUserid(userid);
            communityEntity.setCommunityTitle(upCommunityQ.getName());
            communityEntity.setStatus(1);
            communityEntity.setCreateTime(DateUtils.getCurrentTime());
            communityEntity.setUserNickname(user.getNickname());
            communityEntity.setMemberNumber(1);
            communityService.save(communityEntity);

            //同时通知User服务对应的一个情况
            ManageCommunityEntity manageCommunityEntity = new ManageCommunityEntity();
            manageCommunityEntity.setCommunityImg(communityEntity.getCommunityImg());
            manageCommunityEntity.setCommunityInfo(communityEntity.getCommunityInfo());
            manageCommunityEntity.setCommunityid(communityEntity.getCommunityid());
            manageCommunityEntity.setUserid(communityEntity.getUserid());
            manageCommunityEntity.setCommunityName(communityEntity.getCommunityTitle());
            manageCommunityEntity.setCreateTime(communityEntity.getCreateTime());
            feignManageCommunityService.save(manageCommunityEntity);
            //设置用户日志

            LogManageCommunityEntity logManageCommunityEntity = new LogManageCommunityEntity();
            logManageCommunityEntity.setCommunityName(manageCommunityEntity.getCommunityName());
            logManageCommunityEntity.setCreateTime(manageCommunityEntity.getCreateTime());
            logManageCommunityEntity.setUserid(manageCommunityEntity.getUserid());
            logManageCommunityEntity.setAction(1);
            logManageCommunityEntity.setCommunityid(manageCommunityEntity.getCommunityid());
            feignLogManageCommunityService.save(logManageCommunityEntity);

            //存储成功设置标志
            redisUtils.set(RedisTransKey.setUpCommunityKey(userid)
                    ,1,10, TimeUnit.MINUTES
            );

        }else {
            return R.error(BizCodeEnum.NO_SUCHUSER.getCode(),BizCodeEnum.NO_SUCHUSER.getMsg());
        }

        return R.ok("您的社区创建成功！");
    }

    @Override
    public R upBlog(UpBlogQ upBlogQ) {
        /*
         * 1. 主流程和博文的上传类似
         * 2. 当文章直接通过审核之后，发送消息给博文的上传者
         * 3. 如果博文的审核没有通过，那么通知社区的创建者，创建者进行对应的审核
         * 4. 需要用于审核的内容将在管理员的管理中心进行显示，审核。
         * */
        String userid = upBlogQ.getUserid();

        //接口限流
        if (redisUtils.hasKey(RedisTransKey.getCommunityUpBlog(upBlogQ.getUserid()))) {
            return R.error(BizCodeEnum.OVER_UPBLOG.getCode(), BizCodeEnum.OVER_UPBLOG.getMsg());
        }

        Long communityid = upBlogQ.getCommunityid();
        String backMessage = "您的博文已经成功上传至社区";

        //分别对社区，对用户进行校验，这里博文服务会进行处理
        CommunityEntity comm = communityService.getById(communityid);
        if(comm==null){
            return R.error(BizCodeEnum.NO_SUCHCOMMUNITY.getCode(),BizCodeEnum.NO_SUCHCOMMUNITY.getMsg());
        }

        //判断当前用户是不是具备资格提交博文（社区成员，社区创建者）
        if(!userid.equals(comm.getUserid())&& isJoin(userid, communityid)){
            backMessage="加入该社区后才可发文";
            return R.ok(backMessage);
        }
        /*
         * 这里开始调用博文服务的社区博文上传服务
         * */

        CommunityBlogUpQ communityBlogUpQ = new CommunityBlogUpQ();
        BeanUtils.copyProperties(upBlogQ,communityBlogUpQ);
        communityBlogUpQ.setCommunityName(comm.getCommunityTitle());
        communityBlogUpQ.setCommunityUserid(comm.getUserid());
        R r = feignCommunityBlogUp.communityBlogUp(communityBlogUpQ);
        int status = Integer.parseInt(String.valueOf(r.get("status")));
        if(status==1){
            backMessage = "您的博文直接通过审核，已发布在社区哟~";
            comm.setBlogNumber(comm.getBlogNumber()+1);
        }else if(status==2){
            backMessage = "您的博文正在等待社区管理员审核";
        }else if(status==-1){
            return R.error(BizCodeEnum.BAD_BLOGINFO.getCode(), BizCodeEnum.BAD_BLOGINFO.getMsg());
        }else if(status==-2){
            return R.error(BizCodeEnum.NO_SUCHUSER.getCode(), BizCodeEnum.NO_SUCHUSER.getMsg());
        }
        /*
         * 设置标志
         */
        redisUtils.set(RedisTransKey.getCommunityUpBlog(upBlogQ.getUserid())
                , 1, 1, TimeUnit.MINUTES
        );
        //更新一下状态
        communityService.updateById(comm);
        return R.ok(backMessage);
    }

    @Override
    public R upQuiz(UpQuizQ upQuizQ) {
        /*
         * 用于社区上传提问使用
         * 流程与博文上传类似（lite）
         * */
        String userid = upQuizQ.getUserid();
        //接口限流
        if (redisUtils.hasKey(RedisTransKey.getRedisCommunityUpQuiz(upQuizQ.getUserid()))) {
            return R.error(BizCodeEnum.OVER_UPBLOG.getCode(), BizCodeEnum.OVER_UPBLOG.getMsg());
        }

        Long communityid = upQuizQ.getCommunityid();
        String backMessage = "您的提问已成功发布，正等待小伙伴赶来";
        CommunityEntity comm = communityService.getById(communityid);
        if(comm==null){
            return R.error(BizCodeEnum.NO_SUCHCOMMUNITY.getCode(),BizCodeEnum.NO_SUCHCOMMUNITY.getMsg());
        }

        //判断当前用户是不是具备资格提交博文（社区成员，社区创建者）
        if(!userid.equals(comm.getUserid())&& isJoin(userid, communityid)){
            backMessage="加入该社区后才可提问";
            return R.ok(backMessage);
        }

        //此时也是调用Quiz服务的一个上传
        CommunityUpQuizQ communityUpQuizQ = new CommunityUpQuizQ();
        BeanUtils.copyProperties(upQuizQ,communityUpQuizQ);
        communityUpQuizQ.setCommunityUserid(comm.getUserid());
        communityUpQuizQ.setCommunityName(communityUpQuizQ.getCommunityName());
        R r = feignCommunityUpQuizService.communityUpQuiz(communityUpQuizQ);
        int status = Integer.parseInt(String.valueOf(r.get("status")));

        if(status==1){
            backMessage = "您的提问已成功发布在社区哟~";
        }else if(status==2){
            backMessage = "您的提问正在等待社区管理员进行审核";
        }else if(status==-1){
            return R.error(BizCodeEnum.BAD_QUIZ.getCode(), BizCodeEnum.BAD_QUIZ.getMsg());
        }else if(status==-2){
            return R.error(BizCodeEnum.NO_SUCHUSER.getCode(), BizCodeEnum.NO_SUCHUSER.getMsg());
        }
        /*
         * 设置标志
         */
        redisUtils.set(RedisTransKey.setCommunityUpQuiz(upQuizQ.getUserid())
                , 1, 1, TimeUnit.MINUTES
        );
        return R.ok(backMessage);
    }

    @Override
    public R AuthenticationAnsQ(AuthenticationAnsQ authenticationAnsQ) {
         /*
          * 此接口只是负责完成基本的验证工作，当验证通过之后的话，由
          * Quiz对应Ans服务完成操作。
          * this interface just for base authentication work.
          * -1表示社区不存在，0表示正常，1表示当前的用户没有这个权限（不属于这个社区或者，不是管理员）
          * 当一切都正常的时候，需要返回当前查询到的community信息
          * */
        int status = 0;
        String userid = authenticationAnsQ.getUserid();
        Long communityid = authenticationAnsQ.getCommunityid();
        CommunityEntity comm = communityService.getById(communityid);
        if(comm==null){
            status=-1;
            return R.ok().put("status",status);
        }
        if(!userid.equals(comm.getUserid())&& isJoin(userid, communityid)) {
            status = 1;
        }
        return Objects.requireNonNull(R.ok().put("status", status)).put("community",comm);
    }



    public boolean isJoin(String userid,Long communityid){
        /*
         * 查看当前的成员是不是已经加入咱们的这个社区了
         * */
        Map<String, Object> params = new HashMap<>();
        params.put("userid",userid);
        params.put("communityid",communityid);
        R r = feignCommunityisInService.communityisIn(params);
        String in = FastJsonUtils.toJson(r.get("in"));
        CommunityJoinEntity isJoin = FastJsonUtils.fromJson(in, CommunityJoinEntity.class);
        return isJoin == null;
    }
}
