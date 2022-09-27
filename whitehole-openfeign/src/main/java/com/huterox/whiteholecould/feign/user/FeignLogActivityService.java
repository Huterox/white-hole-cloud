package com.huterox.whiteholecould.feign.user;


import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.user.LogActivityEntity;
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
@RequestMapping("user/logactivity")
public interface FeignLogActivityService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:logactivity:list")
    public R list(@RequestParam Map<String, Object> params);

    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:logactivity:info")
    public R info(@PathVariable("userid") String userid);
    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:logactivity:save")
    public R save(@RequestBody LogActivityEntity logActivity);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:logactivity:update")
    public R update(@RequestBody LogActivityEntity logActivity);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:logactivity:delete")
    public R delete(@RequestBody String[] userids);

}
