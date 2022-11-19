package com.huterox.whitehole.whiteholeblog.service.surface;

import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholeblog.entity.surface.blogUp.UpBlogEntity;
import com.huterox.whiteholecould.entity.blog.Q.CommunityBlogUpQ;

public interface BlogUpService {


    public R blogUp(UpBlogEntity entity);

    public R blogUpdata();

    public R communityBlogUp(CommunityBlogUpQ entity);

}
