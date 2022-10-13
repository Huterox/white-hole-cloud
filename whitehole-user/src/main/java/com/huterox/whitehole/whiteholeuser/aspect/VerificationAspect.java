package com.huterox.whitehole.whiteholeuser.aspect;

import com.alibaba.fastjson.JSON;
import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholeuser.entity.surface.login.LoginToken;
import com.huterox.whitehole.whiteholeuser.entity.surface.register.EmailCodeEntity;
import com.huterox.whitehole.whiteholeuser.exception.BadLoginParamsException;
import com.huterox.whitehole.whiteholeuser.exception.BadLoginTokenException;
import com.huterox.whitehole.whiteholeuser.exception.NotLoginException;
import com.huterox.whitehole.whiteholeuser.utils.LoginType;
import com.huterox.whitehole.whiteholeuser.utils.RedisTransKey;
import com.huterox.whitehole.whiteholeuser.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 负责专门校验，用户有没有登录用的
 * 1. 当前主要负责校验token是否合法
 * */
@Component
@Aspect
@Slf4j
public class VerificationAspect {

    @Autowired
    RedisUtils redisUtils;

    @Pointcut("@annotation(com.huterox.common.holeAnnotation.NeedLogin)")
    public void verification() {}

    /**
     * 环绕通知 @Around ,当然也可以使用 @Before (前置通知)  @After (后置通知)就算了
     * @param proceedingJoinPoint
     * @return
     * 我们这里再直接抛出异常，反正有那个谁统一异常类
     */

    @Around("verification()")
    public Object verification(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        assert servletRequestAttributes != null;
        HttpServletRequest request = servletRequestAttributes.getRequest();

        //正常逻辑是先进行伪参数验证这里先不做

        //分登录的设备进行验证
        String loginType = request.getHeader("loginType");
        String userid = request.getHeader("userid");
        String tokenUser = request.getHeader("loginToken");
        String tokenKey = RedisTransKey.getTokenKey(userid + ":" + loginType);
        if(tokenUser==null || userid==null || loginType==null){
            throw new BadLoginParamsException();
        }
        if(redisUtils.hasKey(tokenKey)){
            if(loginType.equals(LoginType.PcType)){
                Object o = redisUtils.get(tokenKey);
                LoginToken loginToken = JSON.parseObject(o.toString(), LoginToken.class);
                if(!loginToken.getPcLoginToken().equals(tokenUser)){
                    throw new BadLoginTokenException();
                }
            }else if (loginType.equals(LoginType.MobileType)){
                Object o = redisUtils.get(tokenKey);
                LoginToken loginToken = JSON.parseObject(o.toString(), LoginToken.class);
                if(!loginToken.getMobileLoginToken().equals(tokenUser)){
                    throw new BadLoginTokenException();
                }
            }
        }else {
            throw new NotLoginException();
        }

        return proceedingJoinPoint.proceed();
    }
}