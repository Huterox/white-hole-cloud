package com.huterox.whitehole.whiteholeuser.service.surface.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huterox.common.utils.LoginType;
import com.huterox.common.utils.R;
import com.huterox.common.utils.SecurityUtils;
import com.huterox.whitehole.whiteholeuser.entity.surface.login.LoginEntity;
import com.huterox.common.utils.LoginToken;
import com.huterox.whitehole.whiteholeuser.exception.BizCodeEnum;
import com.huterox.whitehole.whiteholeuser.service.base.UserService;
import com.huterox.whitehole.whiteholeuser.service.surface.LoginService;
import com.huterox.whitehole.whiteholeuser.utils.*;
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
                //登录成功，签发token,按照平台类型去签发不同的Token
                String token = JwtTokenUtil.generateToken(User);
                //登录成功后，将userid--->token存redis，便于做登录验证
                String ipAddr = GetIPAddrUtils.GetIPAddr();
                if(entity.getType().equals(LoginType.PcType)){
                    LoginToken loginToken = new LoginToken(token,null,ipAddr);
                    redisUtils.set(RedisTransKey.setTokenKey(User.getUserid()+":"+LoginType.PcType)
                            ,loginToken,7, TimeUnit.DAYS
                    );
                    return Objects.requireNonNull(R.ok(BizCodeEnum.SUCCESSFUL.getMsg())
                                    .put(LoginType.PcLoginToken, token))
                                    .put("userid",User.getUserid());
                }else if (entity.getType().equals(LoginType.MobileType)){
                    LoginToken loginToken = new LoginToken(null,token,ipAddr);
                    redisUtils.set(RedisTransKey.setTokenKey(User.getUserid()+":"+LoginType.MobileType)
                            ,loginToken,7, TimeUnit.DAYS
                    );
                    return Objects.requireNonNull(R.ok(BizCodeEnum.SUCCESSFUL.getMsg())
                                    .put(LoginType.PcLoginToken, token))
                                    .put("userid",User.getUserid());
                } else {
                    return R.error(BizCodeEnum.NUNKNOW_LGINTYPE.getCode(),BizCodeEnum.NUNKNOW_LGINTYPE.getMsg());
                }
            }else {
                return R.error(BizCodeEnum.BAD_PUTDATA.getCode(),BizCodeEnum.BAD_PUTDATA.getMsg());
            }
        }else {
            return R.error(BizCodeEnum.NO_SUCHUSER.getCode(),BizCodeEnum.NO_SUCHUSER.getMsg());
        }
    }
}
