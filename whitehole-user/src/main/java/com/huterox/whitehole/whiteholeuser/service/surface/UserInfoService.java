package com.huterox.whitehole.whiteholeuser.service.surface;

import com.huterox.common.utils.R;


public interface UserInfoService {
    public R userInfo(String userid);
    public R userInfoArticle(String userid);
    public R userInfoQuiz(String userid);
    public R userInfoAns(String userid);
    public R userInfoUnity(String userid);
}
