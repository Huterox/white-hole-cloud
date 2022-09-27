package com.huterox.whiteholecould.feign.activity;

import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.activity.QuizDaysEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:34:46
 */
@FeignClient("activity")
@RequestMapping("whiteholeactivity/quizdays")
public interface FeignQuizDaysService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholeactivity:quizdays:list")
    public R list(@RequestParam Map<String, Object> params);

    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("whiteholeactivity:quizdays:info")
    public R info(@PathVariable("userid") String userid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholeactivity:quizdays:save")
    public R save(@RequestBody QuizDaysEntity quizDays);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholeactivity:quizdays:update")
    public R update(@RequestBody QuizDaysEntity quizDays);
    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholeactivity:quizdays:delete")
    public R delete(@RequestBody String[] userids);

}
