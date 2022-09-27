package com.huterox.whiteholecould.feign.community;


import com.huterox.common.utils.R;

import com.huterox.whiteholecould.entity.community.LikeEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:34:12
 */
@FeignClient("community")
@RequestMapping("whiteholecommunity/like")
public interface FeignLikeService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholecommunity:like:list")
    public R list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/info/{communityid}")
    //RequiresPermissions("whiteholecommunity:like:info")
    public R info(@PathVariable("communityid") Long communityid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholecommunity:like:save")
    public R save(@RequestBody LikeEntity like);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholecommunity:like:update")
    public R update(@RequestBody LikeEntity like);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholecommunity:like:delete")
    public R delete(@RequestBody Long[] communityids);

}
