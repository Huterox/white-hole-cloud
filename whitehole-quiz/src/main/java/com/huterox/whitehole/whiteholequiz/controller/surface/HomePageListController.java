package com.huterox.whitehole.whiteholequiz.controller.surface;

import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholequiz.service.surface.baseUpQuiz.HomePageQuizListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quiz/home")
public class HomePageListController {

    @Autowired
    HomePageQuizListService homePageQuizListService;

    @RequestMapping("/hot")
    @Cacheable(value={"homePageList:hotQuizList"},key = "#root.methodName")
    public R hotQuizList() throws Exception {
        return homePageQuizListService.hotQuizList();
    }

    @RequestMapping("/last")
    @Cacheable(value={"homePageList:lastQuizList"},key = "#root.methodName")
    public R lastQuizList() throws Exception {
        return homePageQuizListService.lastQuizList();
    }


}
