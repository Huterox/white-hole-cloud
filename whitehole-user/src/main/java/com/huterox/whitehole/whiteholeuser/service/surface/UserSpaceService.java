package com.huterox.whitehole.whiteholeuser.service.surface;

import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholeuser.entity.surface.userspace.UserSpaceInfoListQueryEntity;

public interface UserSpaceService {

    public R userIsLogin(String userid);
    public R userAllArticle(UserSpaceInfoListQueryEntity entity);

}