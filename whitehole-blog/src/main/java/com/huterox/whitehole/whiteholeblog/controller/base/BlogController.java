package com.huterox.whitehole.whiteholeblog.controller.base;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.huterox.whiteholecould.entity.blog.BlogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huterox.whitehole.whiteholeblog.service.base.BlogService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:21:46
 */
@RestController
@RequestMapping("whiteholeblog/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholeblog:blog:list")
    public R list(@RequestParam Map<String, Object> params) throws Exception {
        PageUtils page = blogService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("whiteholeblog:blog:info")
    public R info(@PathVariable("userid") String userid){
		BlogEntity blog = blogService.getById(userid);

        return R.ok().put("blog", blog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholeblog:blog:save")
    public R save(@RequestBody BlogEntity blog){
		blogService.save(blog);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholeblog:blog:update")
    public R update(@RequestBody BlogEntity blog){
		blogService.updateById(blog);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholeblog:blog:delete")
    public R delete(@RequestBody String[] userids){
		blogService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
