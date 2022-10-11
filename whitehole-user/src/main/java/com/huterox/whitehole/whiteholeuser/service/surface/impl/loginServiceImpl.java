package com.huterox.whitehole.whiteholeuser.service.surface.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholeuser.entity.surface.login.LoginEntity;
import com.huterox.whitehole.whiteholeuser.exception.BizCodeEnum;
import com.huterox.whitehole.whiteholeuser.service.base.UserService;
import com.huterox.whitehole.whiteholeuser.service.surface.LoginService;
import com.huterox.whitehole.whiteholeuser.utils.JwtTokenUtil;
import com.huterox.whitehole.whiteholeuser.utils.RedisTransKey;
import com.huterox.whitehole.whiteholeuser.utils.RedisUtils;
import com.huterox.whitehole.whiteholeuser.utils.SecurityUtils;
import com.huterox.whiteholecould.entity.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;


@Service
public class loginServiceImpl implements LoginService {

    @Autowired
    UserService userService;
    @Autowired
    RedisUtils redisUtils;
    //为安全期间这里也做一个20防刷
    @Override
    public R Login(LoginEntity entity) {

        String username = entity.getUsername();
        String password = entity.getPassword();
        password=password.replaceAll(" ","");
        if(redisUtils.hasKey(RedisTransKey.getLoginKey(username))){
            return R.error(BizCodeEnum.OVER_REQUESTS.getCode(),BizCodeEnum.OVER_REQUESTS.getMsg());
        }
        redisUtils.set(RedisTransKey.setLoginKey(username),1,20);
        UserEntity User = userService.getOne(
                new QueryWrapper<UserEntity>().eq("username", username)
        );
        if(User!=null){
            if(SecurityUtils.matchesPassword(password,User.getPassword())){
                //登录成功，签发token
                String token = JwtTokenUtil.generateToken(User);
                redisUtils.set(RedisTransKey.setTokenKey(username),token,7, TimeUnit.DAYS);
                return Objects.requireNonNull(R.ok(BizCodeEnum.SUCCESSFUL.getMsg())
                                .put("loginToken", token))
                                .put("userid",User.getUserid());
            }else {
                return R.error(BizCodeEnum.BAD_PUTDATA.getCode(),BizCodeEnum.BAD_PUTDATA.getMsg());
            }
        }else {
            return R.error(BizCodeEnum.NO_SUCHUSER.getCode(),BizCodeEnum.NO_SUCHUSER.getMsg());
        }
    }
}
