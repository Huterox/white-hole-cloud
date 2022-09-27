package com.huterox.whiteholecould.feign.blog;

import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.blog.CommentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 问题回答的评论
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:21:46
 */
@FeignClient("blog")
@RequestMapping("whiteholeblog/comment")
public interface FeignCommentService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholeblog:comment:list")
    public R list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/info/{commentid}")
    //RequiresPermissions("whiteholeblog:comment:info")
    public R info(@PathVariable("commentid") Long commentid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholeblog:comment:save")
    public R save(@RequestBody CommentEntity comment);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholeblog:comment:update")
    public R update(@RequestBody CommentEntity comment);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholeblog:comment:delete")
    public R delete(@RequestBody Long[] commentids);

}
