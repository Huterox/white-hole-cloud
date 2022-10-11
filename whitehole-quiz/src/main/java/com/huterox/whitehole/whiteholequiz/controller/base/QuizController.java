package com.huterox.whitehole.whiteholequiz.controller.base;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.huterox.whiteholecould.entity.quiz.QuizEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huterox.whitehole.whiteholequiz.service.base.QuizService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
@RestController
@RequestMapping("whiteholequiz/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholequiz:quiz:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = quizService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{quizid}")
    //RequiresPermissions("whiteholequiz:quiz:info")
    public R info(@PathVariable("quizid") Long quizid){
		QuizEntity quiz = quizService.getById(quizid);

        return R.ok().put("quiz", quiz);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholequiz:quiz:save")
    public R save(@RequestBody QuizEntity quiz){
		quizService.save(quiz);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholequiz:quiz:update")
    public R update(@RequestBody QuizEntity quiz){
		quizService.updateById(quiz);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholequiz:quiz:delete")
    public R delete(@RequestBody Long[] quizids){
		quizService.removeByIds(Arrays.asList(quizids));

        return R.ok();
    }

}
