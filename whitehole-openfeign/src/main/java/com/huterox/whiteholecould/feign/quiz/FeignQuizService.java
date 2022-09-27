package com.huterox.whiteholecould.feign.quiz;


import com.huterox.common.utils.R;

import com.huterox.whiteholecould.entity.quiz.QuizEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
@FeignClient("quiz")
@RequestMapping("whiteholequiz/quiz")
public interface FeignQuizService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholequiz:quiz:list")
    public R list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/info/{quizid}")
    //RequiresPermissions("whiteholequiz:quiz:info")
    public R info(@PathVariable("quizid") Long quizid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholequiz:quiz:save")
    public R save(@RequestBody QuizEntity quiz);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholequiz:quiz:update")
    public R update(@RequestBody QuizEntity quiz);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholequiz:quiz:delete")
    public R delete(@RequestBody Long[] quizids);
}
