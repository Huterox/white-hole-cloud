package com.huterox.whiteholecould.feign.blog;

import com.huterox.common.utils.R;

import com.huterox.whiteholecould.entity.blog.CommunityBelongEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:21:46
 */
@FeignClient("blog")
@RequestMapping("whiteholeblog/communitybelong")
public interface FeignCommunityBelongService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholeblog:communitybelong:list")
    public R list(@RequestParam Map<String, Object> params);

    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("whiteholeblog:communitybelong:info")
    public R info(@PathVariable("userid") String userid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholeblog:communitybelong:save")
    public R save(@RequestBody CommunityBelongEntity communityBelong);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholeblog:communitybelong:update")
    public R update(@RequestBody CommunityBelongEntity communityBelong);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholeblog:communitybelong:delete")
    public R delete(@RequestBody String[] userids);

}
