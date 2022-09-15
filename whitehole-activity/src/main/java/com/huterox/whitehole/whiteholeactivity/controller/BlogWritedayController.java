package com.huterox.whitehole.whiteholeactivity.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huterox.whitehole.whiteholeactivity.entity.BlogWritedayEntity;
import com.huterox.whitehole.whiteholeactivity.service.BlogWritedayService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:34:46
 */
@RestController
@RequestMapping("whiteholeactivity/blogwriteday")
public class BlogWritedayController {
    @Autowired
    private BlogWritedayService blogWritedayService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholeactivity:blogwriteday:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = blogWritedayService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("whiteholeactivity:blogwriteday:info")
    public R info(@PathVariable("userid") String userid){
		BlogWritedayEntity blogWriteday = blogWritedayService.getById(userid);

        return R.ok().put("blogWriteday", blogWriteday);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholeactivity:blogwriteday:save")
    public R save(@RequestBody BlogWritedayEntity blogWriteday){
		blogWritedayService.save(blogWriteday);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholeactivity:blogwriteday:update")
    public R update(@RequestBody BlogWritedayEntity blogWriteday){
		blogWritedayService.updateById(blogWriteday);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholeactivity:blogwriteday:delete")
    public R delete(@RequestBody String[] userids){
		blogWritedayService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
