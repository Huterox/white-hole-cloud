package com.huterox.whitehole.whiteholeuser.controller.base;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.huterox.whiteholecould.entity.user.LogQuizEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.huterox.whitehole.whiteholeuser.service.base.LogQuizService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@RestController
@RequestMapping("user/logquiz")
public class LogQuizController {
    @Autowired
    private LogQuizService logQuizService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:logquiz:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = logQuizService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:logquiz:info")
    public R info(@PathVariable("userid") String userid){
		LogQuizEntity logQuiz = logQuizService.getById(userid);

        return R.ok().put("logQuiz", logQuiz);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:logquiz:save")
    public R save(@RequestBody LogQuizEntity logQuiz){
		logQuizService.save(logQuiz);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:logquiz:update")
    public R update(@RequestBody LogQuizEntity logQuiz){
		logQuizService.updateById(logQuiz);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:logquiz:delete")
    public R delete(@RequestBody String[] userids){
		logQuizService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
