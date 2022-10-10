package com.huterox.whitehole.whiteholeuser.service.surface.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholeuser.entity.base.UserEntity;
import com.huterox.whitehole.whiteholeuser.entity.surface.register.EmailCodeEntity;
import com.huterox.whitehole.whiteholeuser.entity.surface.register.GetEmailCodeEntity;
import com.huterox.whitehole.whiteholeuser.entity.surface.register.RegisterEntity;
import com.huterox.whitehole.whiteholeuser.exception.BizCodeEnum;
import com.huterox.whitehole.whiteholeuser.service.base.UserService;
import com.huterox.whitehole.whiteholeuser.service.surface.MailService;
import com.huterox.whitehole.whiteholeuser.service.surface.RegisterService;
import com.huterox.whitehole.whiteholeuser.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    UserService userService;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    MailService mailService;
    @Value("${spring.mail.limit}")
    Integer limit;
    @Value("${spring.mail.limitTime}")
    Integer limitTime;

    @Override
    public R register(RegisterEntity entity) {
        String username = entity.getUsername();
        username = username.replaceAll(" ","");
        String emailCode = entity.getEmailCode();
//        先检验一下验证码，对不对，邮箱有没有被更改
        if(redisUtils.hasKey(RedisTransKey.getEmailKey(username))){
            Object o = redisUtils.get(RedisTransKey.getEmailKey(username));
            EmailCodeEntity emailCodeEntity = JSON.parseObject(o.toString(), EmailCodeEntity.class);
            if(username.equals(emailCodeEntity.getUsername())){
                if(emailCode.equals(emailCodeEntity.getEmailCode())){
                    //开始封装用户并进行存储
                    UserEntity userEntity = new UserEntity();
                    userEntity.setEmail(entity.getEmail());
                    userEntity.setNickname(entity.getNickname());
                    userEntity.setPassword(SecurityUtils.encodePassword(
                            entity.getPassword()).replaceAll(" ","")
                    );
//                    用户状态，1-正常 2-警告 3-封禁
                    userEntity.setStatus(1);
                    userEntity.setCreatTime(DateUtils.getCurrentTime());
                    userEntity.setUsername(username);
                    userEntity.setPhone(entity.getPhone());
                    userService.save(userEntity);
                    redisUtils.del(RedisTransKey.getEmailKey(username));
                }else {
                    return R.error(BizCodeEnum.BAD_EMAILCODE_VERIFY.getCode(),BizCodeEnum.BAD_EMAILCODE_VERIFY.getMsg());
                }
            }else {
                return R.error(BizCodeEnum.BAD_DOING.getCode(),BizCodeEnum.BAD_DOING.getMsg());
            }
        }else {
            return R.error(BizCodeEnum.OVER_TIME.getCode(),BizCodeEnum.OVER_TIME.getMsg());
        }
        return R.ok(BizCodeEnum.SUCCESSFUL.getMsg());
    }



    @Override
    public R emailCode(GetEmailCodeEntity entity) {
        String email = entity.getEmail();
        String username = entity.getUsername();
        //判断用户是不是恶意刷邮箱，在规定时间内进行的
        if (redisUtils.hasKey(RedisTransKey.getEmailKey(username))) {
            Object o = redisUtils.get(RedisTransKey.getEmailKey(username));
            EmailCodeEntity emailCodeEntity = JSON.parseObject(o.toString(), EmailCodeEntity.class);
            if (emailCodeEntity.getTimes() >= limit) {
                return R.error(BizCodeEnum.OVER_REQUESTS.getCode(), BizCodeEnum.OVER_REQUESTS.getMsg());
            } else {
//                这里就不去判断两次绑定的邮箱是不是一样的了，不排除第一次输入错了邮箱的情况
                String emailCode = CodeUtils.creatCode(6);
                emailCodeEntity.setEmailCode(emailCode);
                emailCodeEntity.setTimes(emailCodeEntity.getTimes() + 1);
                long overTime = redisUtils.getExpire(username, TimeUnit.MINUTES);
                redisUtils.set(RedisTransKey.setEmailKey(username), emailCodeEntity,
                        overTime, TimeUnit.MINUTES
                );
                mailService.sendCodeMailMessage(email, emailCodeEntity.getEmailCode());
            }
        } else {
            UserEntity User = userService.getOne(
                    new QueryWrapper<UserEntity>().eq("username", username)
            );
            if (User != null) {
                return R.error(BizCodeEnum.HAS_USERNAME.getCode(), BizCodeEnum.HAS_USERNAME.getMsg());
            } else {
                String emailCode = CodeUtils.creatCode(6);
                //            我们这里做一件事情，那就是最多允许用户在10分钟内发送2次的邮箱验证
                //            60s倒计时后用户可以再发送验证码，但是间隔在10分钟内只能再发送1次
                EmailCodeEntity emailCodeEntity = new EmailCodeEntity(
                        emailCode, username,email,1
                );
                redisUtils.set(RedisTransKey.setEmailKey(username), emailCodeEntity,
                        limitTime, TimeUnit.MINUTES
                );
                mailService.sendCodeMailMessage(email, emailCodeEntity.getEmailCode());
            }
        }
        return R.ok(BizCodeEnum.SUCCESSFUL.getMsg());
    }
}