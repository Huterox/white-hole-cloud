package com.huterox.whiteholecould.feign.user;


import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.user.LogFriendEntity;
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
@RequestMapping("user/logfriend")
public interface FeignLogFriendService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:logfriend:list")
    public R list(@RequestParam Map<String, Object> params);

    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:logfriend:info")
    public R info(@PathVariable("userid") String userid);
    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:logfriend:save")
    public R save(@RequestBody LogFriendEntity logFriend);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:logfriend:update")
    public R update(@RequestBody LogFriendEntity logFriend);
    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:logfriend:delete")
    public R delete(@RequestBody String[] userids);

}
