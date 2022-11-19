package com.huterox.whitehole.whiteholeuser.controller.surface;


import com.huterox.common.holeAnnotation.NeedLogin;
import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholeuser.entity.surface.userspace.*;
import com.huterox.whitehole.whiteholeuser.service.surface.UserSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/space")
public class UserSpaceController {
    @Autowired
    UserSpaceService userSpaceService;


    /**
     * 这部分是我们最基本的用户相关的一些接口
     * */

    @RequestMapping("/isLogin")
    @NeedLogin
    public R userIsLogin(@RequestParam(value="userid") String userid){
        //只是用来验证用户有没有登录的，也就是校验本地的token信息，如果不对用户将重新登录
        return userSpaceService.userIsLogin(userid);
    }

    @RequestMapping("/userShowInfo")
    @NeedLogin
    public R userShowInfo(@RequestParam(value="userid") String userid){
        return userSpaceService.userShowInfo(userid);
    }

    @RequestMapping("/userShowInfoEditor")
    @NeedLogin
    public R userShowInfoEditor(@RequestParam(value="userid") String userid){
        return userSpaceService.userShowInfoEditor(userid);
    }

    @PostMapping("/userInfoEditor")
    @NeedLogin
    public R userInfoEditor(@Validated @RequestBody UserInfoEditEntity userInfoEditEntity){
        return userSpaceService.userInfoEditor(userInfoEditEntity);
    }
    @PostMapping("/userUpImg")
    @NeedLogin
    public R userUpImg(@Validated @RequestBody UserHandImgEntity entity){
        //上传用户的头像
        return userSpaceService.userUpImg(entity);
    }

    @PostMapping("/userSpaceChangeEmailCode")
    @NeedLogin
    public R userSpaceChangeEmailCode(@Validated @RequestBody UserSpaceEmailCodeEntity entity){
        return userSpaceService.emailCode(entity);
    }

    @PostMapping("/userSpaceChangePassword")
    @NeedLogin
    public R userSpaceChangePassword(@Validated @RequestBody UserSpaceChangePasswordEntity entity){
        return userSpaceService.userChangePassword(entity);
    }


    /**
     * 这部分是关于文章管理部分的接口
     * */
    @RequestMapping("/allArticle")
    @NeedLogin
    public R userAllArticle(UserSpaceInfoListQueryEntity entity) throws Exception {
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
    public R userStatusArticle(UserSpaceInfoListQueryEntity entity) throws Exception {
        return userSpaceService.userStatusArticle(entity);
    }

    @RequestMapping("/privateArticle")
    @NeedLogin
    public R userPrivateArticle(UserSpaceInfoListQueryEntity entity){
        return userSpaceService.userPrivateArticle(entity);
    }


    /**
     * 这部分是关于问答部分的接口
     * */
    @RequestMapping("/allQuiz")
    @NeedLogin
    public R userAllQuiz(UserSpaceInfoListQueryEntity entity) throws Exception {
        return userSpaceService.userAllQuiz(entity);
    }
    @RequestMapping("/allAns")
    @NeedLogin
    public R userAllQAns(UserSpaceInfoListQueryEntity entity) throws Exception {
        return userSpaceService.userAllAns(entity);
    }


    /**
     * 这部分是关于社区部分的接口
     * */

    @RequestMapping("/joinUnity")
    @NeedLogin
    public R userJoinUnity(UserSpaceInfoListQueryEntity entity) throws Exception {
        return userSpaceService.userJoinUnity(entity);
    }

    @RequestMapping("/manageUnity")
    @NeedLogin
    public R userManageUnity(UserSpaceInfoListQueryEntity entity) throws Exception {
        return userSpaceService.userManagerUnity(entity);
    }

}
