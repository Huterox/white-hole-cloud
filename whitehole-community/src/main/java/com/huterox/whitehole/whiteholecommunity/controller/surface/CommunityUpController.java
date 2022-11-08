package com.huterox.whitehole.whiteholecommunity.controller.surface;

import com.huterox.common.holeAnnotation.NeedLogin;
import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholecommunity.entity.surface.communityUp.Q.UpCommunityQ;
import com.huterox.whitehole.whiteholecommunity.service.surface.CommunityUpService;
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
}
