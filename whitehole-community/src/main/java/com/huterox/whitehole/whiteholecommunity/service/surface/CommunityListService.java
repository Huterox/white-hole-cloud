package com.huterox.whitehole.whiteholecommunity.service.surface;

import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholecommunity.entity.surface.communityList.Q.GetunityListQ;

public interface CommunityListService {

    public R unityList(GetunityListQ entity) throws Exception;

    public R unityBlogList(GetunityListQ entity) throws Exception;

    public R unityQuiList(GetunityListQ entity) throws Exception;
}
