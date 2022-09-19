package com.huterox.whiteholecould.feign.community;

import com.huterox.common.utils.R;

import com.huterox.whiteholecould.entity.community.CommunityEntity;
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
@RequestMapping("whiteholecommunity/community")
public interface FeignCommunityService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholecommunity:community:list")
    public R list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/info/{communityid}")
    //RequiresPermissions("whiteholecommunity:community:info")
    public R info(@PathVariable("communityid") Long communityid);
    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholecommunity:community:save")
    public R save(@RequestBody CommunityEntity community);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholecommunity:community:update")
    public R update(@RequestBody CommunityEntity community);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholecommunity:community:delete")
    public R delete(@RequestBody Long[] communityids);

}
