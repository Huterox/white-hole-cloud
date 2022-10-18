package com.huterox.whitehole.whiteholeuser.service.surface;

import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholeuser.entity.surface.userspace.*;

public interface UserSpaceService {

    public R userShowInfo(String userid);
    public R userIsLogin(String userid);
    public R userShowInfoEditor(String userid);
    public R userInfoEditor(UserInfoEditEntity userInfoEditEntity);
    public R userUpImg(UserHandImgEntity entity);
    public R emailCode(UserSpaceEmailCodeEntity entity);
    public R userChangePassword(UserSpaceChangePasswordEntity entity);

    public R userAllArticle(UserSpaceInfoListQueryEntity entity) throws Exception;
    public R userStatusArticle(UserSpaceInfoListQueryEntity entity) throws Exception;
    public R userPrivateArticle(UserSpaceInfoListQueryEntity entity);
    public R userForkArticle(UserSpaceInfoListQueryEntity entity);



}
