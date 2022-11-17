package com.huterox.whitehole.whiteholehole.controller.surface;
import com.huterox.common.holeAnnotation.NeedLogin;
import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholehole.entity.surface.diary.DiaryQ;
import com.huterox.whitehole.whiteholehole.entity.surface.diary.DiaryUpQ;
import com.huterox.whitehole.whiteholehole.service.surface.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hole/diary")
public class DiaryController {

    private DiaryService diaryService;

    @Autowired
    public void setDiaryService(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @RequestMapping("/diarys")
    @NeedLogin
    public R diaryList(@Validated DiaryQ diaryQ) throws Exception {
        return diaryService.diaryList(diaryQ);
    }

    @PostMapping("/upDiary")
    @NeedLogin
    public R upDiary(@Validated @RequestBody DiaryUpQ diaryUpQ){
        return diaryService.upDiary(diaryUpQ);
    }
}
