package com.huterox.whiteholecould.feign.blog;

import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.blog.ComComentEntity;
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
@RequestMapping("whiteholeblog/comcoment")
public interface FeignComComentService {


    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholeblog:comcoment:list")
    public R list(@RequestParam Map<String, Object> params);

    /**
     * 信息
     */
    @RequestMapping("/info/{comComentid}")
    //RequiresPermissions("whiteholeblog:comcoment:info")
    public R info(@PathVariable("comComentid") Long comComentid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholeblog:comcoment:save")
    public R save(@RequestBody ComComentEntity comComent);
    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholeblog:comcoment:update")
    public R update(@RequestBody ComComentEntity comComent);
    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholeblog:comcoment:delete")
    public R delete(@RequestBody Long[] comComentids);
}
