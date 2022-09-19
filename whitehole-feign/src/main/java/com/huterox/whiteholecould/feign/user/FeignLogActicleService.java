package com.huterox.whiteholecould.feign.user;


import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.user.LogActicleEntity;
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
@RequestMapping("user/logacticle")
public interface FeignLogActicleService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:logacticle:list")
    public R list(@RequestParam Map<String, Object> params);

    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:logacticle:info")
    public R info(@PathVariable("userid") String userid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:logacticle:save")
    public R save(@RequestBody LogActicleEntity logActicle);
    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:logacticle:update")
    public R update(@RequestBody LogActicleEntity logActicle);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:logacticle:delete")
    public R delete(@RequestBody String[] userids);

}
