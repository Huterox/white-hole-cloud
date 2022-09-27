package com.huterox.whiteholecould.feign.user;


import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.user.LogBaseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


/**
 * 用户的一些日志信息
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@FeignClient("user")
@RequestMapping("user/logbase")
public interface FeignLogBaseService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:logbase:list")
    public R list(@RequestParam Map<String, Object> params);

    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:logbase:info")
    public R info(@PathVariable("userid") String userid);
    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:logbase:save")
    public R save(@RequestBody LogBaseEntity logBase);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:logbase:update")
    public R update(@RequestBody LogBaseEntity logBase);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:logbase:delete")
    public R delete(@RequestBody String[] userids);
}
