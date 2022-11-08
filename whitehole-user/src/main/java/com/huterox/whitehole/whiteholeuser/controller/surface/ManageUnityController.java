package com.huterox.whitehole.whiteholeuser.controller.surface;
import com.huterox.common.holeAnnotation.NeedLogin;
import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholeuser.entity.surface.unity.unityMListEntity;
import com.huterox.whitehole.whiteholeuser.service.surface.UnityManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/unityM")
public class ManageUnityController {

    @Autowired
    UnityManagerService unityManagerService;

    @RequestMapping("/allArticle")
    @NeedLogin
    public R AllArticle(unityMListEntity entity) throws Exception {
        return unityManagerService.AllArticle(entity);
    }

    @RequestMapping("/statusArticle")
    @NeedLogin
    public R StatusArticle(unityMListEntity entity) throws Exception {
        return unityManagerService.StatusArticle(entity);
    }

    @RequestMapping("/allQuiz")
    @NeedLogin
    public R AllQuiz(unityMListEntity entity) throws Exception {
        return unityManagerService.AllQuiz(entity);
    }

    @RequestMapping("/statusQuiz")
    @NeedLogin
    public R StatusQuiz(unityMListEntity entity) throws Exception {
        return unityManagerService.StatusQuiz(entity);
    }

    @RequestMapping("/statusAns")
    @NeedLogin
    public R StatusAns(unityMListEntity entity) throws Exception {
        return unityManagerService.StatusAns(entity);
    }

    @RequestMapping("/allUser")
    @NeedLogin
    public R AllUser(unityMListEntity entity) throws Exception {
        return unityManagerService.AllUser(entity);
    }

    @RequestMapping("/statusUser")
    @NeedLogin
    public R StatusUser(unityMListEntity entity) throws Exception {
        return unityManagerService.StatusUser(entity);
    }


}
