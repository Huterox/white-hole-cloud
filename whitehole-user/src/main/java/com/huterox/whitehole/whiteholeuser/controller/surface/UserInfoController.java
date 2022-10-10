package com.huterox.whitehole.whiteholeuser.controller.surface;

import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholeuser.service.surface.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;
    @RequestMapping("/userinfo")
    public R userInfo(@RequestParam(value="userid") String userid){
        return userInfoService.userInfo(userid);
    }
    @RequestMapping("/userinfo/userarticle")
    public R userInfoArticle(@RequestParam(value="userid") String userid){
        return userInfoService.userInfo(userid);
    }
    @RequestMapping("/userinfo/userquizList")
    public R userInfoQuiz(@RequestParam(value="userid") String userid){
        return userInfoService.userInfo(userid);
    }
    @RequestMapping("/userinfo/useransList")
    public R userInfoAns(@RequestParam(value="userid") String userid){
        return userInfoService.userInfo(userid);
    }
    @RequestMapping("/userinfo/userunityList")
    public R userInfoUnity(@RequestParam(value="userid") String userid){
        return userInfoService.userInfo(userid);
    }


}
