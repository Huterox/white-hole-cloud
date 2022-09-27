package com.huterox.whiteholecould.feign.community;

import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;

import com.huterox.whiteholecould.entity.community.BlogsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:34:12
 */
@FeignClient("community")
@RequestMapping("whiteholecommunity/blogs")
public interface FeignBlogsService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholecommunity:blogs:list")
    public R list(@RequestParam Map<String, Object> params);

    /**
     * 信息
     */
    @RequestMapping("/info/{communityid}")
    //RequiresPermissions("whiteholecommunity:blogs:info")
    public R info(@PathVariable("communityid") Long communityid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholecommunity:blogs:save")
    public R save(@RequestBody BlogsEntity blogs);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholecommunity:blogs:update")
    public R update(@RequestBody BlogsEntity blogs);
    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholecommunity:blogs:delete")
    public R delete(@RequestBody Long[] communityids);

}
