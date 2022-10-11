package com.huterox.whitehole.whiteholeuser.controller.surface;

import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholeuser.entity.surface.userinfo.UserInfoListQueryEntity;
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
    public R userInfoArticle(UserInfoListQueryEntity entity){
        return userInfoService.userInfoArticle(entity);
    }
    @RequestMapping("/userinfo/userquizList")
    public R userInfoQuiz(UserInfoListQueryEntity entity){
        return userInfoService.userInfoQuiz(entity);
    }
    @RequestMapping("/userinfo/useransList")
    public R userInfoAns(UserInfoListQueryEntity entity){
        return userInfoService.userInfoAns(entity);
    }
    @RequestMapping("/userinfo/userunityList")
    public R userInfoUnity(UserInfoListQueryEntity entity){
        return userInfoService.userInfoUnity(entity);
    }


}
