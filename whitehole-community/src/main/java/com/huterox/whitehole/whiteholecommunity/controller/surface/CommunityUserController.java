package com.huterox.whitehole.whiteholecommunity.controller.surface;

import com.huterox.common.holeAnnotation.NeedLogin;
import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholecommunity.entity.surface.communityJoin.Q.AcJoinQ;
import com.huterox.whitehole.whiteholecommunity.entity.surface.communityJoin.Q.JoinQ;
import com.huterox.whitehole.whiteholecommunity.service.surface.CommunityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/community/join")
public class CommunityUserController {

    @Autowired
    CommunityUserService communityUserService;

    @PostMapping("/join")
    @NeedLogin
    public R join(@Validated @RequestBody JoinQ joinQ){
        return communityUserService.JoinUser(joinQ);
    }

    @PostMapping("/acJoin")
    @NeedLogin
    public R acJoin(@Validated @RequestBody AcJoinQ acJoinQ){
        return communityUserService.AcJoinUser(acJoinQ);
    }
}
