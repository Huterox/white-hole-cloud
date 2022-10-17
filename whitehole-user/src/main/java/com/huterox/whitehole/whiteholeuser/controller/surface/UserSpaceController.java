package com.huterox.whitehole.whiteholeuser.controller.surface;


import com.huterox.common.holeAnnotation.NeedLogin;
import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholeuser.entity.surface.userspace.UserSpaceInfoListQueryEntity;
import com.huterox.whitehole.whiteholeuser.service.surface.UserSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/space")
public class UserSpaceController {
    @Autowired
    UserSpaceService userSpaceService;


    @RequestMapping("/isLogin")
    @NeedLogin
    public R userIsLogin(@RequestParam(value="userid") String userid){
        //只是用来验证用户有没有登录的，也就是校验本地的token信息，如果不对用户将重新登录
        return userSpaceService.userIsLogin(userid);
    }

    @RequestMapping("/allArticle")
    @NeedLogin
    public R userAllArticle(UserSpaceInfoListQueryEntity entity){
        //查询用户所有的博客的接口，这个是个人页面使用的接口，所以必须验证
        return userSpaceService.userAllArticle(entity);
    }

    @RequestMapping("/forkArticle")
    @NeedLogin
    public R userForkArticle(UserSpaceInfoListQueryEntity entity){
        return userSpaceService.userForkArticle(entity);
    }

    @RequestMapping("/statusArticle")
    @NeedLogin
    public R userStatusArticle(UserSpaceInfoListQueryEntity entity){
        return userSpaceService.userStatusArticle(entity);
    }

    @RequestMapping("/privateArticle")
    @NeedLogin
    public R userPrivateArticle(UserSpaceInfoListQueryEntity entity){
        return userSpaceService.userPrivateArticle(entity);
    }

    @RequestMapping("/joinUnity")
    @NeedLogin
    public R userJoinUnity(UserSpaceInfoListQueryEntity entity){
        return null;
    }

    @RequestMapping("/manageUnity")
    @NeedLogin
    public R userManageUnity(UserSpaceInfoListQueryEntity entity){
        return null;
    }




}
