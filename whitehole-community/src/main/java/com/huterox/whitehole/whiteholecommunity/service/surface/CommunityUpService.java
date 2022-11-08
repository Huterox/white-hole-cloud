package com.huterox.whitehole.whiteholecommunity.service.surface;

import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholecommunity.entity.surface.communityUp.Q.UpBlogQ;
import com.huterox.whitehole.whiteholecommunity.entity.surface.communityUp.Q.UpCommunityQ;
import com.huterox.whitehole.whiteholecommunity.entity.surface.communityUp.Q.UpQuizQ;

public interface CommunityUpService {

    public R upCommunity(UpCommunityQ upCommunityQ);

    public R upBlog(UpBlogQ upBlogQ);

    public R upQuiz(UpQuizQ upQuizQ);


}
