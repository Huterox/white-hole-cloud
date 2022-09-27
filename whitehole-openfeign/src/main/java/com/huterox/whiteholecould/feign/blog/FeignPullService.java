package com.huterox.whiteholecould.feign.blog;

import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.blog.PullEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:21:46
 */
@FeignClient("blog")
@RequestMapping("whiteholeblog/pull")
public interface FeignPullService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholeblog:pull:list")
    public R list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("whiteholeblog:pull:info")
    public R info(@PathVariable("userid") String userid);
    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholeblog:pull:save")
    public R save(@RequestBody PullEntity pull);
    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholeblog:pull:update")
    public R update(@RequestBody PullEntity pull);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholeblog:pull:delete")
    public R delete(@RequestBody String[] userids);

}
