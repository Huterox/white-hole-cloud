package com.huterox.whitehole.whiteholehole.service.surface;


import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholehole.entity.surface.diary.DiaryQ;
import com.huterox.whitehole.whiteholehole.entity.surface.diary.DiaryUpQ;

public interface DiaryService {

    public R diaryList(DiaryQ diaryQ) throws Exception;

    public R upDiary(DiaryUpQ diaryUpQ);

}
