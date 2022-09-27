package com.huterox.whiteholecould.feign.user;

import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.user.AchievementEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 用户的一些信息的记录，例如用户的签到天数，用户的称号，称号分为，技术类型，活跃类型，社交类型

每个类型设置4个，但是现在由于还在测试构建阶段，所以的话，我们就只设置一个活跃类型的，并且只有一个，也就是我们的签到天数。



 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@FeignClient("user")
@RequestMapping("user/achievement")
public interface FeignAchievementService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:achievement:list")
    public R list(@RequestParam Map<String, Object> params);

    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:achievement:info")
    public R info(@PathVariable("userid") String userid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:achievement:save")
    public R save(@RequestBody AchievementEntity achievement);
    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:achievement:update")
    public R update(@RequestBody AchievementEntity achievement);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:achievement:delete")
    public R delete(@RequestBody String[] userids);
}
