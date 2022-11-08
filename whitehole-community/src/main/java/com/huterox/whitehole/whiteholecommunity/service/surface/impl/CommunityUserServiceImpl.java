package com.huterox.whitehole.whiteholecommunity.service.surface.impl;

import com.huterox.common.utils.DateUtils;
import com.huterox.common.utils.FastJsonUtils;
import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholecommunity.entity.surface.communityJoin.Q.AcJoinQ;
import com.huterox.whitehole.whiteholecommunity.entity.surface.communityJoin.Q.JoinQ;
import com.huterox.whitehole.whiteholecommunity.exception.BizCodeEnum;
import com.huterox.whitehole.whiteholecommunity.service.base.CommunityService;
import com.huterox.whitehole.whiteholecommunity.service.base.UsersService;
import com.huterox.whitehole.whiteholecommunity.service.surface.CommunityUserService;
import com.huterox.whitehole.whiteholecommunity.utils.RedisTransKey;
import com.huterox.whitehole.whiteholecommunity.utils.RedisUtils;
import com.huterox.whiteholecould.entity.community.CommunityEntity;
import com.huterox.whiteholecould.entity.community.UsersEntity;
import com.huterox.whiteholecould.entity.message.Q.HoleAduitMsgQ;
import com.huterox.whiteholecould.entity.user.CommunityJoinEntity;
import com.huterox.whiteholecould.entity.user.HeadimgEntity;
import com.huterox.whiteholecould.entity.user.UserEntity;
import com.huterox.whiteholecould.feign.message.FeignHoleAduitMsgService;
import com.huterox.whiteholecould.feign.user.FeignCommunityJoinService;
import com.huterox.whiteholecould.feign.user.FeignCommunityisInService;
import com.huterox.whiteholecould.feign.user.FeignHeadimgService;
import com.huterox.whiteholecould.feign.user.FeignUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service
public class CommunityUserServiceImpl implements CommunityUserService {

    @Autowired
    FeignUserService feignUserService;

    @Autowired
    CommunityService communityService;

    @Autowired
    FeignHoleAduitMsgService feignHoleAduitMsgService;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    FeignHeadimgService feignHeadimgService;

    @Autowired
    FeignCommunityJoinService feignCommunityJoinService;

    @Autowired
    UsersService usersService;

    @Autowired
    FeignCommunityisInService feignCommunityisInService;

    @Override
    public R JoinUser(JoinQ joinQ) {
        /**
         * 这里负责用户社区的加入
         * 这个userid是谁要加入我们这个社区
         * 1. 判断用户是不是加入了
         * 2. 通知社区对应的管理员，让管理员进行审核
         * 3. 管理员审核通过后，完成用户的加入
         * 4. 这个接口限制1分钟一次
         * */
        String userid = joinQ.getUserid();
        Long communityid = joinQ.getCommunityid();
        String backMessage = "已经通知社区管理员请等待哟~";

        if(redisUtils.hasKey(RedisTransKey.getRedisJoinCommunity(userid+"_"+communityid))){
            return R.error(BizCodeEnum.OVER_REQUEST.getCode(), BizCodeEnum.OVER_REQUEST.getMsg());
        }

        //分别对社区，对用户进行校验
        CommunityEntity comm = communityService.getById(communityid);
        if(comm==null){
            return R.error(BizCodeEnum.NO_SUCHCOMMUNITY.getCode(),BizCodeEnum.NO_SUCHCOMMUNITY.getMsg());
        }

        //此时查看当前是不是自己加入自己的社区
        if(comm.getUserid().equals(userid)){
            backMessage="看来您对您自己的社区很满意哇~";
            return R.ok(backMessage);
        }

        R info = feignUserService.info(userid);
        String userString = FastJsonUtils.toJson(info.get("user"));
        UserEntity user = FastJsonUtils.fromJson(userString, UserEntity.class);
        if(user==null){
            return R.error(BizCodeEnum.NO_SUCHUSER.getCode(),BizCodeEnum.NO_SUCHUSER.getMsg());
        }
        //都通过之后的话，我们开始发送消息给服务,这个是审核消息，直接发送即可

        //查看当前的这个成员是不是已经加入了
        Map<String, Object> params = new HashMap<>();
        params.put("userid",user.getUserid());
        params.put("communityid",communityid);
        R r = feignCommunityisInService.communityisIn(params);
        String in = FastJsonUtils.toJson(r.get("in"));
        CommunityJoinEntity isJoin = FastJsonUtils.fromJson(in, CommunityJoinEntity.class);
        //此时已经存在了
        if(isJoin !=null){
            backMessage = "您已经加入该社区了";
            redisUtils.set(RedisTransKey.setJoinCommunityKey(userid)
                    ,1,1, TimeUnit.MINUTES
            );
            return R.ok(backMessage);
        }

        HoleAduitMsgQ holeAduitMsgQ = new HoleAduitMsgQ();
        holeAduitMsgQ.setMsgtitle("加入社区申请");
        holeAduitMsgQ.setMsg(user.getNickname()+"申请加入:"+comm.getCommunityTitle()+"社区");
        //消息是转发给管理员的
        holeAduitMsgQ.setUserid(comm.getUserid());
        holeAduitMsgQ.setLinkid(userid);
        holeAduitMsgQ.setLinkid2(String.valueOf(communityid));
//        1-文章，2-提问，3-回答，4-pushAc，5-Merge，6-成员，7-成员请求成功
        holeAduitMsgQ.setType(6);
        feignHoleAduitMsgService.holeAduitMsg(holeAduitMsgQ);
        //存储成功设置标志
        redisUtils.set(RedisTransKey.setJoinCommunityKey(userid+"_"+communityid)
                ,1,10, TimeUnit.MINUTES
        );
        return R.ok(backMessage);
    }

    @Override
    public R AcJoinUser(AcJoinQ acJoinQ) {
        /**
         * 这里进行10秒限制
         * 1. 先进行疯狂校验
         * 2. 进行存储
         * 3. 通知用户
         * */
        String userid = acJoinQ.getUserid();
        String backMessage = "操作完成，已通知该成员";
        if(redisUtils.hasKey(RedisTransKey.getAcAuthority(userid))){
            return R.error(BizCodeEnum.TOO_FASTAUTHORITY.getCode(), BizCodeEnum.TOO_FASTAUTHORITY.getMsg());
        }

        Long communityid = acJoinQ.getCommunityid();
        String membersid = acJoinQ.getMembersid();

        //先对社区进行校验
        CommunityEntity comm = communityService.getById(communityid);
        if(comm==null){
            return R.error(BizCodeEnum.NO_SUCHCOMMUNITY.getCode(),BizCodeEnum.NO_SUCHCOMMUNITY.getMsg());
        }
        String manageID = comm.getUserid();
        if(!manageID.equals(userid)){
            //此时可以判断为非法越权操作，直接封杀2个小时
            redisUtils.set(RedisTransKey.setAcAuthority(userid)
                    ,1,120, TimeUnit.MINUTES
            );
            return R.error(BizCodeEnum.BAD_AUTHORITY.getCode(),BizCodeEnum.BAD_AUTHORITY.getMsg());
        }

        //对用户和成员进行校验
        R info = feignUserService.info(userid);
        String userString = FastJsonUtils.toJson(info.get("user"));
        UserEntity manager = FastJsonUtils.fromJson(userString, UserEntity.class);
        if(manager==null){
            return R.error(BizCodeEnum.NO_SUCHUSER.getCode(),BizCodeEnum.NO_SUCHUSER.getMsg());
        }
        info = feignUserService.info(membersid);
        userString = FastJsonUtils.toJson(info.get("user"));
        UserEntity members = FastJsonUtils.fromJson(userString, UserEntity.class);
        if(members==null){
            return R.error(BizCodeEnum.NO_SUCHUSER.getCode(),BizCodeEnum.NO_SUCHUSER.getMsg());
        }

        UsersEntity usersEntity = new UsersEntity();
        CommunityJoinEntity communityJoinEntity = new CommunityJoinEntity();

        //查看当前的这个成员是不是已经加入了
        Map<String, Object> params = new HashMap<>();
        params.put("userid",membersid);
        params.put("communityid",communityid);
        R r = feignCommunityisInService.communityisIn(params);
        String in = FastJsonUtils.toJson(r.get("in"));
        CommunityJoinEntity isJoin = FastJsonUtils.fromJson(in, CommunityJoinEntity.class);
        //此时已经存在了
        if(isJoin !=null){
            backMessage = "您已加入此社区";
            redisUtils.set(RedisTransKey.setAcAuthority(manageID)
                    ,1,10, TimeUnit.SECONDS
            );
            return R.ok(backMessage);
        }

        //查询成员的头像信息
        R RHeadImg = feignHeadimgService.headimg(membersid);
        String headImgString = FastJsonUtils.toJson(RHeadImg.get("headimg"));
        HeadimgEntity headimg_members = FastJsonUtils.fromJson(headImgString, HeadimgEntity.class);
        if(headimg_members!=null){
            usersEntity.setUserimg(headimg_members.getImgpath());
        }
        //查询管理员头像信息
        RHeadImg = feignHeadimgService.headimg(manageID);
        headImgString = FastJsonUtils.toJson(RHeadImg.get("headimg"));
        HeadimgEntity headimg_manage = FastJsonUtils.fromJson(headImgString, HeadimgEntity.class);
        if(headimg_manage!=null){
            communityJoinEntity.setCommunityImg(headimg_manage.getImgpath());
        }

        //此时进行存储
        usersEntity.setUserid(membersid);
        usersEntity.setUserNickname(members.getNickname());
        usersEntity.setCommunityid(communityid);
        usersEntity.setCommunityName(comm.getCommunityTitle());
        usersEntity.setCreateTime(DateUtils.getCurrentTime());
        usersService.save(usersEntity);

        //更新成员的一个社区加入的情况
        communityJoinEntity.setCommunityImg(comm.getCommunityImg());
        communityJoinEntity.setCommunityInfo(comm.getCommunityInfo());
        communityJoinEntity.setCommunityid(comm.getCommunityid());
        communityJoinEntity.setCommunityUserid(comm.getUserid());
        communityJoinEntity.setCommunityUserNickname(comm.getUserNickname());
        communityJoinEntity.setCommunityName(comm.getCommunityTitle());
        communityJoinEntity.setUserid(membersid);
        communityJoinEntity.setCommunityJoinTime(DateUtils.getCurrentTime());
        feignCommunityJoinService.save(communityJoinEntity);

        //此时向用户发送消息，告诉用户社区申请通过

        HoleAduitMsgQ holeAduitMsgQ = new HoleAduitMsgQ();
        holeAduitMsgQ.setMsgtitle("您的申请已经通过");
        holeAduitMsgQ.setMsg("Congratulations，成为"+comm.getCommunityTitle()+"的一员！");
        holeAduitMsgQ.setUserid(membersid);
        holeAduitMsgQ.setLinkid(String.valueOf(comm.getCommunityid()));
//        1-文章，2-提问，3-回答，4-pushAc，5-Merge，6-成员，7-成员请求成功
        holeAduitMsgQ.setType(7);
        feignHoleAduitMsgService.holeAduitMsg(holeAduitMsgQ);
        //存储成功设置标志
        redisUtils.set(RedisTransKey.setAcAuthority(manageID)
                ,1,10, TimeUnit.SECONDS
        );
        backMessage = "操作完成，已通知该成员";
        return R.ok(backMessage);
    }


}
