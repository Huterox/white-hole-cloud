package com.huterox.whitehole.whiteholeblog.controller.base;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.huterox.whiteholecould.entity.blog.CommentLikeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huterox.whitehole.whiteholeblog.service.base.CommentLikeService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:21:46
 */
@RestController
@RequestMapping("whiteholeblog/commentlike")
public class CommentLikeController {
    @Autowired
    private CommentLikeService commentLikeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholeblog:commentlike:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = commentLikeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("whiteholeblog:commentlike:info")
    public R info(@PathVariable("userid") String userid){
		CommentLikeEntity commentLike = commentLikeService.getById(userid);

        return R.ok().put("commentLike", commentLike);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholeblog:commentlike:save")
    public R save(@RequestBody CommentLikeEntity commentLike){
		commentLikeService.save(commentLike);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholeblog:commentlike:update")
    public R update(@RequestBody CommentLikeEntity commentLike){
		commentLikeService.updateById(commentLike);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholeblog:commentlike:delete")
    public R delete(@RequestBody String[] userids){
		commentLikeService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
