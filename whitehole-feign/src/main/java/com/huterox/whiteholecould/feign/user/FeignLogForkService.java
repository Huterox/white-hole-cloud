package com.huterox.whiteholecould.feign.user;


import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.user.LogForkEntity;
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
@RequestMapping("user/logfork")
public interface FeignLogForkService {


    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:logfork:list")
    public R list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:logfork:info")
    public R info(@PathVariable("userid") String userid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:logfork:save")
    public R save(@RequestBody LogForkEntity logFork);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:logfork:update")
    public R update(@RequestBody LogForkEntity logFork);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:logfork:delete")
    public R delete(@RequestBody String[] userids);

}
