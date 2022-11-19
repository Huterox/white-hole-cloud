package com.huterox.whitehole.whiteholecommunity.service.surface;

import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholecommunity.entity.surface.communityJoin.Q.AcJoinQ;
import com.huterox.whitehole.whiteholecommunity.entity.surface.communityJoin.Q.JoinQ;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


public interface CommunityUserService {

    public R JoinUser(JoinQ joinQ);

    public R AcJoinUser(AcJoinQ acJoinQ);

}
