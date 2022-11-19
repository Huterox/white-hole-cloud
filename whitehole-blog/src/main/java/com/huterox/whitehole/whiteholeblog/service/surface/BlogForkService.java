package com.huterox.whitehole.whiteholeblog.service.surface;

import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholeblog.entity.surface.fork.UpForkEntity;

public interface BlogForkService {

    public R forkBlog(UpForkEntity entity);

    public R viewPull();

    public R acceptMerge();
}
