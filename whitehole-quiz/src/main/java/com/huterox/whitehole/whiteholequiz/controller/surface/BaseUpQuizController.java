package com.huterox.whitehole.whiteholequiz.controller.surface;

import com.huterox.common.holeAnnotation.NeedLogin;
import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholequiz.entity.surface.baseUpQuiz.UpQuizEntity;
import com.huterox.whitehole.whiteholequiz.service.surface.baseUpQuiz.BaseUpQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quiz/base")
public class BaseUpQuizController {
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

}
