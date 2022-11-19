package com.huterox.whitehole.whiteholecommunity.service.surface;


import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholecommunity.entity.surface.communityAcQ.AcAnsQ;
import com.huterox.whitehole.whiteholecommunity.entity.surface.communityAcQ.AcBlogQ;
import com.huterox.whitehole.whiteholecommunity.entity.surface.communityAcQ.AcQuizQ;

public interface CommunityContentAcService {

    public R AcBlog(AcBlogQ acBlogQ);

    public R AcQuiz(AcQuizQ acQuizQ);

    public R AcAns(AcAnsQ acAnsQ);

}
