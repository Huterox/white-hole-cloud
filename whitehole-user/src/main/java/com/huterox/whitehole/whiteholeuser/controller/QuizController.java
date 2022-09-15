package com.huterox.whitehole.whiteholeuser.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huterox.whitehole.whiteholeuser.entity.QuizEntity;
import com.huterox.whitehole.whiteholeuser.service.QuizService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 这个表是中间表，不存在主键。
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@RestController
@RequestMapping("user/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:quiz:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = quizService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:quiz:info")
    public R info(@PathVariable("userid") String userid){
		QuizEntity quiz = quizService.getById(userid);

        return R.ok().put("quiz", quiz);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:quiz:save")
    public R save(@RequestBody QuizEntity quiz){
		quizService.save(quiz);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:quiz:update")
    public R update(@RequestBody QuizEntity quiz){
		quizService.updateById(quiz);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:quiz:delete")
    public R delete(@RequestBody String[] userids){
		quizService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
