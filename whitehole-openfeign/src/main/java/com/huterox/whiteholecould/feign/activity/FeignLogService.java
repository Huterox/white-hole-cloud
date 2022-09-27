package com.huterox.whiteholecould.feign.activity;

import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;

import com.huterox.whiteholecould.entity.activity.LogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:34:46
 */
@FeignClient("activity")
@RequestMapping("whiteholeactivity/log")
public interface FeignLogService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholeactivity:log:list")
    public R list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/info/{actorid}")
    //RequiresPermissions("whiteholeactivity:log:info")
    public R info(@PathVariable("actorid") String actorid);
    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholeactivity:log:save")
    public R save(@RequestBody LogEntity log);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholeactivity:log:update")
    public R update(@RequestBody LogEntity log);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholeactivity:log:delete")
    public R delete(@RequestBody String[] actorids);

}
