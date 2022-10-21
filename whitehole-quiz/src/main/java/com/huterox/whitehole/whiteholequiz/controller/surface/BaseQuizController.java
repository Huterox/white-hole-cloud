package com.huterox.whitehole.whiteholequiz.controller.surface;

import com.huterox.common.holeAnnotation.NeedLogin;
import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholequiz.entity.surface.baseUpQuiz.GetQuizAnsListEntity;
import com.huterox.whitehole.whiteholequiz.entity.surface.baseUpQuiz.GetQuizListEntity;
import com.huterox.whitehole.whiteholequiz.entity.surface.baseUpQuiz.UpAnsEntity;
import com.huterox.whitehole.whiteholequiz.entity.surface.baseUpQuiz.UpQuizEntity;
import com.huterox.whitehole.whiteholequiz.service.surface.baseUpQuiz.BaseUpQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz/base")
public class BaseQuizController {
    /**
     * 这个提问的接口是专门负责我们基本的一个提问的处理的
     * 这个部分处理的都是需要经过我们的后台管理进行审核的
     * 由于是开发阶段，所以的话，我们这边的话就是默认ok吧
     * 等我们完整的开发完毕之后的话，我们默认改回来
     * */
    @Autowired
    BaseUpQuizService baseUpQuizService;

    @PostMapping("/upQuiz")
    @NeedLogin
    public R baseUpQuiz(@Validated @RequestBody UpQuizEntity entity){
        return baseUpQuizService.BaseUpQuiz(entity);
    }

    @RequestMapping("/lastQuiz")
    @Cacheable(value={"baseQuiz:lastQuiz"},key = "#entity.page")
    public R lastQuiz(@Validated GetQuizListEntity entity) throws Exception {
        return baseUpQuizService.lastQuiz(entity);
    }

    @Cacheable(value={"baseQuiz:hotQuizAns"},key = "#root.methodName")
    @RequestMapping("/hotQuiz")
    public R hotQuizAns() throws Exception {
        return baseUpQuizService.hotQuizAns();
    }


    @PostMapping("/baseUpAns")
    @NeedLogin
    public R baseUpAns(@Validated @RequestBody UpAnsEntity entity) throws Exception {
        return baseUpQuizService.baseUpAns(entity);
    }

    @RequestMapping("/lastQuizAns")
    public R lastQuizAns(@Validated GetQuizAnsListEntity entity) throws Exception {
        return baseUpQuizService.lastQuizAns(entity);
    }

    @RequestMapping("/quizById")
    public R quizById(@RequestParam(value="quizid")Long quizid) throws Exception {
        return baseUpQuizService.quizById(quizid);
    }



}
