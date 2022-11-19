package com.huterox.whitehole.whiteholecommunity.controller.surface;

import com.huterox.common.holeAnnotation.NeedLogin;
import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholecommunity.entity.surface.communityUp.Q.UpBlogQ;
import com.huterox.whitehole.whiteholecommunity.entity.surface.communityUp.Q.UpCommunityQ;
import com.huterox.whitehole.whiteholecommunity.entity.surface.communityUp.Q.UpQuizQ;
import com.huterox.whitehole.whiteholecommunity.service.surface.CommunityUpService;
import com.huterox.whiteholecould.entity.community.Q.AuthenticationAnsQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/community/up")
public class CommunityUpController {

    @Autowired
    CommunityUpService communityUpService;

    @PostMapping("/upcommunity")
    @NeedLogin
    public R upCommunity(@Validated @RequestBody UpCommunityQ communityQ){
        return communityUpService.upCommunity(communityQ);
    }

    @PostMapping("/upBlog")
    @NeedLogin
    public R upBlog(@Validated @RequestBody UpBlogQ upBlogQ){
        return communityUpService.upBlog(upBlogQ);
    }

    @PostMapping("/upQuiz")
    @NeedLogin
    public R upQuiz(@Validated @RequestBody UpQuizQ upQuizQ){
        return communityUpService.upQuiz(upQuizQ);
    }


    @PostMapping("/authenticationAnsQ")
    public R AuthenticationAnsQ(@Validated @RequestBody AuthenticationAnsQ upAnsQ){
        return communityUpService.AuthenticationAnsQ(upAnsQ);
    }
}
