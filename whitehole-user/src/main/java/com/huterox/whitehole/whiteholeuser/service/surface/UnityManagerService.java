package com.huterox.whitehole.whiteholeuser.service.surface;


import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholeuser.entity.surface.unity.unityMListEntity;

public interface UnityManagerService {

    public R AllArticle(unityMListEntity entity);
    public R StatusArticle(unityMListEntity entity);

    public R AllQuiz(unityMListEntity entity);
    public R StatusQuiz(unityMListEntity entity);
    public R StatusAns(unityMListEntity entity);

    public R AllUser(unityMListEntity entity);
    public R StatusUser(unityMListEntity entity);

}
