package com.huterox.whitehole.whiteholeuser.service.surface.impl;

import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholeuser.entity.surface.userspace.UserSpaceInfoListQueryEntity;
import com.huterox.whitehole.whiteholeuser.exception.BizCodeEnum;
import com.huterox.whitehole.whiteholeuser.service.surface.UserSpaceService;
import org.springframework.stereotype.Service;

@Service
public class UserSpaceServiceImpl implements UserSpaceService {
    @Override
    public R userIsLogin(String userid) {
        return R.ok(BizCodeEnum.SUCCESSFUL.getMsg());
    }

    @Override
    public R userInfoSpace(UserSpaceInfoListQueryEntity entity) {
        return null;
    }

    @Override
    public R userInfoArticleSpace(UserSpaceInfoListQueryEntity entity) {
        return null;
    }

    @Override
    public R userInfoQuizSpace(UserSpaceInfoListQueryEntity entity) {
        return null;
    }

    @Override
    public R userInfoAnsSpace(UserSpaceInfoListQueryEntity entity) {
        return null;
    }

    @Override
    public R userInfoUnitySpace(UserSpaceInfoListQueryEntity entity) {
        return null;
    }
}
