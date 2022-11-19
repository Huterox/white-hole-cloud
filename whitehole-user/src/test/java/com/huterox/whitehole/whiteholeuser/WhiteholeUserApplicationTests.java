package com.huterox.whitehole.whiteholeuser;

import com.alibaba.fastjson.JSON;
import com.huterox.whitehole.whiteholeuser.service.surface.MailService;
import com.huterox.whitehole.whiteholeuser.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest

class WhiteholeUserApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    RedisUtils redisUtils;

    @Test
    public void redisSave(){
        User user = new User();
        user.setAge(18);
        user.setHeight(17.5);
        user.setName("s");
        boolean flag = redisUtils.set("Go:huterox", user,1000, TimeUnit.SECONDS);
        System.out.println(flag?"存储成功":"存储失败");
    }

    @Test
    public void redisRead(){
        User huterox = JSON.parseObject(redisUtils.get("huterox").toString(),User.class);
        System.out.println("得到的结果是:"+huterox);
    }

    //        发送邮箱测试
    @Autowired
    MailService mailService;
    @Test
    public void emailTest(){

        String to = "3139541502@qq.com";
        String subject = "测试邮件";
        String content = "just for test";
        mailService.sendTextMailMessage(to,subject,content);

    }


}
