package com.huterox.whiteholecould.feign.blog;


import com.huterox.common.utils.R;

import com.huterox.whiteholecould.entity.blog.PullContentEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 当用户同意之后，那么博文对应的内容就会被覆盖，同时在blog的贡献表当中显示提交用户的信息！
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:21:46
 */
@FeignClient("blog")
@RequestMapping("whiteholeblog/pullcontent")
public interface FeignContentService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholeblog:pullcontent:list")
    public R list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("whiteholeblog:pullcontent:info")
    public R info(@PathVariable("userid") String userid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholeblog:pullcontent:save")
    public R save(@RequestBody PullContentEntity pullContent);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholeblog:pullcontent:update")
    public R update(@RequestBody PullContentEntity pullContent);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholeblog:pullcontent:delete")
    public R delete(@RequestBody String[] userids);

}
