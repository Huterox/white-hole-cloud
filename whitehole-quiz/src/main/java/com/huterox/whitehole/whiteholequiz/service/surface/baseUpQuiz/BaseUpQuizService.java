package com.huterox.whitehole.whiteholequiz.service.surface.baseUpQuiz;

import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholequiz.entity.surface.baseUpQuiz.GetQuizAnsListEntity;
import com.huterox.whitehole.whiteholequiz.entity.surface.baseUpQuiz.GetQuizListEntity;
import com.huterox.whitehole.whiteholequiz.entity.surface.baseUpQuiz.UpAnsEntity;
import com.huterox.whitehole.whiteholequiz.entity.surface.baseUpQuiz.UpQuizEntity;

public interface BaseUpQuizService {

    public R BaseUpQuiz(UpQuizEntity entity);

    public R lastQuiz(GetQuizListEntity entity) throws Exception;

    public R baseUpAns(UpAnsEntity entity);

    public R lastQuizAns(GetQuizAnsListEntity entity) throws Exception;

    public R hotQuiz() throws Exception;

    public R quizById(Long quizid);
}
