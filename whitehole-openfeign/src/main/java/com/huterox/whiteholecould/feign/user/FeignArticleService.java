package com.huterox.whiteholecould.feign.user;


import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.user.ArticleEntity;
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
@RequestMapping("user/article")
public interface FeignArticleService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:article:list")
    public R list(@RequestParam Map<String, Object> params);

    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:article:info")
    public R info(@PathVariable("userid") String userid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:article:save")
    public R save(@RequestBody ArticleEntity article);
    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:article:update")
    public R update(@RequestBody ArticleEntity article);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:article:delete")
    public R delete(@RequestBody String[] userids);
}
