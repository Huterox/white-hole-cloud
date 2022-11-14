package com.huterox.whitehole.whiteholeuser.service.surface;


import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholeuser.entity.surface.unity.UnityMListEntity;

public interface UnityManagerService {

    public R AllArticle(UnityMListEntity entity) throws Exception;
    public R StatusArticle(UnityMListEntity entity) throws Exception;

    public R AllQuiz(UnityMListEntity entity) throws Exception;
    public R StatusQuiz(UnityMListEntity entity) throws Exception;
    public R StatusAns(UnityMListEntity entity) throws Exception;

    public R AllUser(UnityMListEntity entity) throws Exception;
    public R StatusUser(UnityMListEntity entity);

}
