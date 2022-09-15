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

import com.huterox.whitehole.whiteholeuser.entity.AchievementEntity;
import com.huterox.whitehole.whiteholeuser.service.AchievementService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 用户的一些信息的记录，例如用户的签到天数，用户的称号，称号分为，技术类型，活跃类型，社交类型

每个类型设置4个，但是现在由于还在测试构建阶段，所以的话，我们就只设置一个活跃类型的，并且只有一个，也就是我们的签到天数。



 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@RestController
@RequestMapping("user/achievement")
public class AchievementController {
    @Autowired
    private AchievementService achievementService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:achievement:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = achievementService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:achievement:info")
    public R info(@PathVariable("userid") String userid){
		AchievementEntity achievement = achievementService.getById(userid);

        return R.ok().put("achievement", achievement);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:achievement:save")
    public R save(@RequestBody AchievementEntity achievement){
		achievementService.save(achievement);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:achievement:update")
    public R update(@RequestBody AchievementEntity achievement){
		achievementService.updateById(achievement);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:achievement:delete")
    public R delete(@RequestBody String[] userids){
		achievementService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
