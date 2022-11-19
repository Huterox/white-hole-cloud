package com.huterox.whiteholecould.feign.user;


import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.user.LabelBlogLikeEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 这个是用户感兴趣的博文标签
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@FeignClient("user")
@RequestMapping("user/labelbloglike")
public interface FeignLabelBlogLikeService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:labelbloglike:list")
    public R list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:labelbloglike:info")
    public R info(@PathVariable("userid") String userid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:labelbloglike:save")
    public R save(@RequestBody LabelBlogLikeEntity labelBlogLike);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:labelbloglike:update")
    public R update(@RequestBody LabelBlogLikeEntity labelBlogLike);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:labelbloglike:delete")
    public R delete(@RequestBody String[] userids);

}
