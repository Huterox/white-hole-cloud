package com.huterox.whiteholecould.feign.user;

import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.user.UserEntity;
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
@RequestMapping("user/user")
public interface UserController {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:user:list")
    public R list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:user:info")
    public R info(@PathVariable("userid") String userid);
    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:user:save")
    public R save(@RequestBody UserEntity user);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:user:update")
    public R update(@RequestBody UserEntity user);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:user:delete")
    public R delete(@RequestBody String[] userids);

}
