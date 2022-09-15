package com.huterox.whitehole.whiteholeactivity.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huterox.whitehole.whiteholeactivity.entity.QuizDaysEntity;
import com.huterox.whitehole.whiteholeactivity.service.QuizDaysService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:34:46
 */
@RestController
@RequestMapping("whiteholeactivity/quizdays")
public class QuizDaysController {
    @Autowired
    private QuizDaysService quizDaysService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholeactivity:quizdays:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = quizDaysService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("whiteholeactivity:quizdays:info")
    public R info(@PathVariable("userid") String userid){
		QuizDaysEntity quizDays = quizDaysService.getById(userid);

        return R.ok().put("quizDays", quizDays);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholeactivity:quizdays:save")
    public R save(@RequestBody QuizDaysEntity quizDays){
		quizDaysService.save(quizDays);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholeactivity:quizdays:update")
    public R update(@RequestBody QuizDaysEntity quizDays){
		quizDaysService.updateById(quizDays);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholeactivity:quizdays:delete")
    public R delete(@RequestBody String[] userids){
		quizDaysService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
