package com.huterox.whiteholecould.feign.activity;


import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.activity.ActivityEntity;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 这个是我们创建的活动的一些内容，这个是在运营阶段由管理人员创建的，当运营确定好活动之后，
我们这边就会进行创建新的活动，之后在技术层面实现，注意：activity 和 admin 都是管理人使用的模块。

注意的是，我们的这个记录的是我们这个由管理人员创建的玩意来的，实际上我们还有系统自动的一些活动，这些活动是系统级别的。




 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:34:46
 */
@FeignClient("activity")
@RequestMapping("whiteholeactivity/activity")
public interface FeignActivtyService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholeactivity:activity:list")
    public R list(@RequestParam Map<String, Object> params);

    /**
     * 信息
     */
    @RequestMapping("/info/{activityid}")
    //RequiresPermissions("whiteholeactivity:activity:info")
    public R info(@PathVariable("activityid") Long activityid);
    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholeactivity:activity:save")
    public R save(@RequestBody ActivityEntity activity);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholeactivity:activity:update")
    public R update(@RequestBody ActivityEntity activity);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholeactivity:activity:delete")
    public R delete(@RequestBody Long[] activityids);

}
