package com.huterox.whitehole.whiteholeuser.service.surface;

import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholeuser.entity.surface.userinfo.UserInfoListQueryEntity;


public interface UserInfoService {
    public R userInfo(String userid);
    public R userInfoArticle(UserInfoListQueryEntity entity);
    public R userInfoQuiz(UserInfoListQueryEntity entity);
    public R userInfoAns(UserInfoListQueryEntity entity);
    public R userInfoUnity(UserInfoListQueryEntity entity);
}
