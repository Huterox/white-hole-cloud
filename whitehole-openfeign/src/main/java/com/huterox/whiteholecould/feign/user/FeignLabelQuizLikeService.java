package com.huterox.whiteholecould.feign.user;


import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.user.LabelQuizLikeEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@FeignClient("user")
@RequestMapping("user/labelquizlike")
public interface FeignLabelQuizLikeService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:labelquizlike:list")
    public R list(@RequestParam Map<String, Object> params);
    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:labelquizlike:info")
    public R info(@PathVariable("userid") String userid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:labelquizlike:save")
    public R save(@RequestBody LabelQuizLikeEntity labelQuizLike);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:labelquizlike:update")
    public R update(@RequestBody LabelQuizLikeEntity labelQuizLike);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:labelquizlike:delete")
    public R delete(@RequestBody String[] userids);

}
