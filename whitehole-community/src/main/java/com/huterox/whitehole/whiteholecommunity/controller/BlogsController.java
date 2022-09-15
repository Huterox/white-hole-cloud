package com.huterox.whitehole.whiteholecommunity.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huterox.whitehole.whiteholecommunity.entity.BlogsEntity;
import com.huterox.whitehole.whiteholecommunity.service.BlogsService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:34:12
 */
@RestController
@RequestMapping("whiteholecommunity/blogs")
public class BlogsController {
    @Autowired
    private BlogsService blogsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholecommunity:blogs:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = blogsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{communityid}")
    //RequiresPermissions("whiteholecommunity:blogs:info")
    public R info(@PathVariable("communityid") Long communityid){
		BlogsEntity blogs = blogsService.getById(communityid);

        return R.ok().put("blogs", blogs);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholecommunity:blogs:save")
    public R save(@RequestBody BlogsEntity blogs){
		blogsService.save(blogs);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholecommunity:blogs:update")
    public R update(@RequestBody BlogsEntity blogs){
		blogsService.updateById(blogs);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholecommunity:blogs:delete")
    public R delete(@RequestBody Long[] communityids){
		blogsService.removeByIds(Arrays.asList(communityids));

        return R.ok();
    }

}
