package com.huterox.whiteholecould.feign.user;


import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.user.LogManageCommunityEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@FeignClient("user")
@RequestMapping("user/logmanagecommunity")
public interface FeignLogManageCommunityService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:logmanagecommunity:list")
    public R list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:logmanagecommunity:info")
    public R info(@PathVariable("userid") String userid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:logmanagecommunity:save")
    public R save(@RequestBody LogManageCommunityEntity logManageCommunity);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:logmanagecommunity:update")
    public R update(@RequestBody LogManageCommunityEntity logManageCommunity);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:logmanagecommunity:delete")
    public R delete(@RequestBody String[] userids);
}
