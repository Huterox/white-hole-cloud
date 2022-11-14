package com.huterox.whitehole.whiteholecommunity.controller.surface;


import com.huterox.common.holeAnnotation.NeedLogin;
import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholecommunity.entity.surface.communityAcQ.AcAnsQ;
import com.huterox.whitehole.whiteholecommunity.entity.surface.communityAcQ.AcBlogQ;
import com.huterox.whitehole.whiteholecommunity.entity.surface.communityAcQ.AcQuizQ;
import com.huterox.whitehole.whiteholecommunity.service.surface.CommunityContentAcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 主要负责实现对社区内的文章，提问，回答进行审核
 * 当系统审核置信度较低时，需要通知社区管理员进行审核
 * */

@RestController
@RequestMapping("/community/ac")
public class CommunityContentAcController {

    @Autowired
    CommunityContentAcService communityContentAcService;

    @PostMapping("/AcBlog")
    @NeedLogin
    public R AcBlog(AcBlogQ acBlogQ){
        return communityContentAcService.AcBlog(acBlogQ);
    }

    @PostMapping("/AcQuiz")
    @NeedLogin
    public R AcQuiz(AcQuizQ acQuizQ){
        return communityContentAcService.AcQuiz(acQuizQ);
    }
    @PostMapping("/AcAns")
    @NeedLogin
    public R AcAns(AcAnsQ acAnsQ){
        return communityContentAcService.AcAns(acAnsQ);
    }

}
