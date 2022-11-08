package com.huterox.whitehole.whiteholethirdpart.service;

import com.huterox.common.utils.R;

public interface OssService {

    public R policy(String bucket,Long expireTime);
    public R policy();
    public R userSpaceUpImgPolicy();

    public R quizWriteAnsImgPolicy();
    public R blogWriteImgPolicy();
    public R blogFaceImgPolicy();

    public R communityImgPolicy();
}
