package com.huterox.whitehole.whiteholeuser.service.surface.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholeuser.entity.base.HeadimgEntity;
import com.huterox.whitehole.whiteholeuser.entity.base.UserEntity;
import com.huterox.whitehole.whiteholeuser.entity.surface.userinfo.UserInfoEntity;
import com.huterox.whitehole.whiteholeuser.exception.BizCodeEnum;
import com.huterox.whitehole.whiteholeuser.service.base.HeadimgService;
import com.huterox.whitehole.whiteholeuser.service.base.UserService;
import com.huterox.whitehole.whiteholeuser.service.surface.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *这里面的所有方法是用来查询用户信息的，所有的求取格式为Get
 * @param userid
 * */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserService userService;
    @Autowired
    HeadimgService headimgService;

    @Override
    public R userInfo(String userid) {
        UserEntity User = userService.getOne(
                new QueryWrapper<UserEntity>().eq("userid", userid)
        );
        if(User!=null){
            UserInfoEntity userInfoEntity = new UserInfoEntity();
            userInfoEntity.setUserid(User.getUserid());
            userInfoEntity.setUserinfo(User.getInfo());
            userInfoEntity.setNickname(User.getNickname());
            //查询用户的头像信息
            HeadimgEntity headimg = headimgService.getOne(
                    new QueryWrapper<HeadimgEntity>().eq("userid", userid)
                            .orderByDesc("imgid")
                            .last("limit 0,1")

            );
            if(headimg!=null){
                userInfoEntity.setUserpic(headimg.getImgpath());
            }else {
                userInfoEntity.setUserpic(null);
            }
//            可以不写BizCodeEnum.SUCCESSFUL.getMsg()，默认有填写
            return R.ok(BizCodeEnum.SUCCESSFUL.getMsg())
                    .put("User",userInfoEntity);

        }else {
            return R.error(BizCodeEnum.NO_SUCHUSER.getCode(),BizCodeEnum.NO_SUCHUSER.getMsg());
        }
    }


    @Override
    public R userInfoArticle(String userid) {
        return null;
    }

    @Override
    public R userInfoQuiz(String userid) {
        return null;
    }

    @Override
    public R userInfoAns(String userid) {
        return null;
    }

    @Override
    public R userInfoUnity(String userid) {
        return null;
    }
}
