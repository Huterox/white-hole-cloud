package com.huterox.whiteholecould.feign.user;


import com.huterox.common.utils.R;

import com.huterox.whiteholecould.entity.user.BlogForkEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@FeignClient("user")
@RequestMapping("user/blogfork")
public interface FeignBlogForkService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:blogfork:list")
    public R list(@RequestParam Map<String, Object> params);

    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:blogfork:info")
    public R info(@PathVariable("userid") String userid);
    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:blogfork:save")
    public R save(@RequestBody BlogForkEntity blogFork);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:blogfork:update")
    public R update(@RequestBody BlogForkEntity blogFork);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:blogfork:delete")
    public R delete(@RequestBody String[] userids);

    @RequestMapping("/deletefork")
    //RequiresPermissions("user:blogfork:delete")
    public R deletefork(@RequestBody Map<String, Object> params);
}
