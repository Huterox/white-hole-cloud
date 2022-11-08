package com.huterox.whiteholecould.feign.community;

import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.community.QuizsEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("whiteholecommunity/quizs")
public interface FeignQuizsService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholecommunity:quizs:list")
    public R list(@RequestParam Map<String, Object> params);

    /**
     * 信息
     */
    @RequestMapping("/info/{communityid}")
    //RequiresPermissions("whiteholecommunity:quizs:info")
    public R info(@PathVariable("communityid") Long communityid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholecommunity:quizs:save")
    public R save(@RequestBody QuizsEntity quizs);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholecommunity:quizs:update")
    public R update(@RequestBody QuizsEntity quizs);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholecommunity:quizs:delete")
    public R delete(@RequestBody Long[] communityids);
}
