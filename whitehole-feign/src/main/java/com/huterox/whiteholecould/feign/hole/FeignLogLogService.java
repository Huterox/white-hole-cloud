package com.huterox.whiteholecould.feign.hole;


import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.hole.LogLogEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 目前的话，我们的树洞只有两种功能，所以的话，我们这边就按照功能进行建表。
现在这个表是关于用户日志的表。
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:37:20
 */
@FeignClient("hole")
@RequestMapping("whiteholehole/loglog")
public interface FeignLogLogService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholehole:loglog:list")
    public R list(@RequestParam Map<String, Object> params);

    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("whiteholehole:loglog:info")
    public R info(@PathVariable("userid") String userid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholehole:loglog:save")
    public R save(@RequestBody LogLogEntity logLog);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholehole:loglog:update")
    public R update(@RequestBody LogLogEntity logLog);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholehole:loglog:delete")
    public R delete(@RequestBody String[] userids);
}
