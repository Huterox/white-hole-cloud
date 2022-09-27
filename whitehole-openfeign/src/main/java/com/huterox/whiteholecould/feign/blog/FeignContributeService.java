package com.huterox.whiteholecould.feign.blog;

import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.blog.ContributeEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 博客的贡献者列表 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:21:46
 */
@FeignClient("blog")
@RequestMapping("whiteholeblog/contribute")
public interface FeignContributeService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholeblog:contribute:list")
    public R list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("whiteholeblog:contribute:info")
    public R info(@PathVariable("userid") String userid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholeblog:contribute:save")
    public R save(@RequestBody ContributeEntity contribute);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholeblog:contribute:update")
    public R update(@RequestBody ContributeEntity contribute);
    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholeblog:contribute:delete")
    public R delete(@RequestBody String[] userids);

}
