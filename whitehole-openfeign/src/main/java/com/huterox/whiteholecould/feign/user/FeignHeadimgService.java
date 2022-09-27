package com.huterox.whiteholecould.feign.user;

import com.huterox.common.utils.R;

import com.huterox.whiteholecould.entity.user.HeadimgEntity;
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
@RequestMapping("user/headimg")
public interface FeignHeadimgService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:headimg:list")
    public R list(@RequestParam Map<String, Object> params);

    /**
     * 信息
     */
    @RequestMapping("/info/{imgid}")
    //RequiresPermissions("user:headimg:info")
    public R info(@PathVariable("imgid") Long imgid);
    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:headimg:save")
    public R save(@RequestBody HeadimgEntity headimg);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:headimg:update")
    public R update(@RequestBody HeadimgEntity headimg);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:headimg:delete")
    public R delete(@RequestBody Long[] imgids);

}
