package com.huterox.whitehole.whiteholeuser.service.surface;

import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholeuser.entity.surface.userspace.UserSpaceInfoListQueryEntity;

public interface UserSpaceService {

    public R userIsLogin(String userid);
    public R userInfoSpace(UserSpaceInfoListQueryEntity entity);
    public R userInfoArticleSpace(UserSpaceInfoListQueryEntity entity);
    public R userInfoQuizSpace(UserSpaceInfoListQueryEntity entity);
    public R userInfoAnsSpace(UserSpaceInfoListQueryEntity entity);
    public R userInfoUnitySpace(UserSpaceInfoListQueryEntity entity);
}
