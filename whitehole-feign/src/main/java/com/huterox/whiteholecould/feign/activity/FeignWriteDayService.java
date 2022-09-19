package com.huterox.whiteholecould.feign.activity;

import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.activity.BlogWritedayEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:34:46
 */
@FeignClient("activity")
@RequestMapping("whiteholeactivity/blogwriteday")
public interface FeignWriteDayService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholeactivity:blogwriteday:list")
    public R list(@RequestParam Map<String, Object> params);

    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("whiteholeactivity:blogwriteday:info")
    public R info(@PathVariable("userid") String userid);
    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholeactivity:blogwriteday:save")
    public R save(@RequestBody BlogWritedayEntity blogWriteday);
    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholeactivity:blogwriteday:update")
    public R update(@RequestBody BlogWritedayEntity blogWriteday);
    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholeactivity:blogwriteday:delete")
    public R delete(@RequestBody String[] userids);
}
