package com.huterox.whitehole.whiteholequiz.service.surface.baseUpQuiz.impl;

import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholequiz.entity.surface.baseUpQuiz.GetQuizListEntity;
import com.huterox.whitehole.whiteholequiz.service.base.QuizService;
import com.huterox.whitehole.whiteholequiz.service.surface.baseUpQuiz.BaseUpQuizService;
import com.huterox.whitehole.whiteholequiz.service.surface.baseUpQuiz.HomePageQuizListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomePageQuizListServiceImpl implements HomePageQuizListService {

    @Autowired
    BaseUpQuizService baseUpQuizService;

    @Autowired
    QuizService quizService;


    @Override
    public R hotQuizList() throws Exception {
        return baseUpQuizService.hotQuiz();
    }

    @Override
    public R lastQuizList() throws Exception {
        GetQuizListEntity entity = new GetQuizListEntity();
        entity.setPage(1);
        entity.setLimit(10);
        //这个userid是没有用的，只是拿过来占位置的
        entity.setUserid("1");
        return baseUpQuizService.lastQuiz(entity);
    }
}
