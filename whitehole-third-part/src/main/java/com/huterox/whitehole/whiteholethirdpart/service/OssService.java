package com.huterox.whitehole.whiteholethirdpart.service;

import com.huterox.common.utils.R;

public interface OssService {

    public R policy(String bucket);
    public R policy();
    public R userSpaceUpImgPolicy();
}
