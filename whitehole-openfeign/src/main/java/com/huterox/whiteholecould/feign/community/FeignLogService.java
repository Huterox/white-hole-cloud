package com.huterox.whiteholecould.feign.community;


import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.community.LogEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:34:12
 */
@RestController
@RequestMapping("whiteholecommunity/log")
public interface FeignLogService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholecommunity:log:list")
    public R list(@RequestParam Map<String, Object> params);

    /**
     * 信息
     */
    @RequestMapping("/info/{communityid}")
    //RequiresPermissions("whiteholecommunity:log:info")
    public R info(@PathVariable("communityid") Long communityid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholecommunity:log:save")
    public R save(@RequestBody LogEntity log);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholecommunity:log:update")
    public R update(@RequestBody LogEntity log);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholecommunity:log:delete")
    public R delete(@RequestBody Long[] communityids);

}
