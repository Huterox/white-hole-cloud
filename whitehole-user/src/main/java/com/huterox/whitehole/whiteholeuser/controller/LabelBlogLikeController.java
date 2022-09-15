package com.huterox.whitehole.whiteholeuser.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huterox.whitehole.whiteholeuser.entity.LabelBlogLikeEntity;
import com.huterox.whitehole.whiteholeuser.service.LabelBlogLikeService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 这个是用户感兴趣的博文标签
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@RestController
@RequestMapping("user/labelbloglike")
public class LabelBlogLikeController {
    @Autowired
    private LabelBlogLikeService labelBlogLikeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:labelbloglike:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = labelBlogLikeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:labelbloglike:info")
    public R info(@PathVariable("userid") String userid){
		LabelBlogLikeEntity labelBlogLike = labelBlogLikeService.getById(userid);

        return R.ok().put("labelBlogLike", labelBlogLike);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:labelbloglike:save")
    public R save(@RequestBody LabelBlogLikeEntity labelBlogLike){
		labelBlogLikeService.save(labelBlogLike);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:labelbloglike:update")
    public R update(@RequestBody LabelBlogLikeEntity labelBlogLike){
		labelBlogLikeService.updateById(labelBlogLike);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:labelbloglike:delete")
    public R delete(@RequestBody String[] userids){
		labelBlogLikeService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
