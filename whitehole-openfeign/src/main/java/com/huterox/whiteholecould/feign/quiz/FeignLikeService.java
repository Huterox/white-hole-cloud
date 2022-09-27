package com.huterox.whiteholecould.feign.quiz;


import com.huterox.common.utils.R;

import com.huterox.whiteholecould.entity.quiz.LikeEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 收藏一定是喜欢，喜欢一定收藏，所以只要用户点击了收藏，就默认喜欢，我们对问题的受欢迎度进行筛选就是先通过收藏数，然后是喜欢数，最后是浏览量来搞的。


 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
@FeignClient("quiz")
@RequestMapping("whiteholequiz/like")
public interface FeignLikeService {


    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholequiz:like:list")
    public R list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("whiteholequiz:like:info")
    public R info(@PathVariable("userid") String userid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholequiz:like:save")
    public R save(@RequestBody LikeEntity like);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholequiz:like:update")
    public R update(@RequestBody LikeEntity like);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholequiz:like:delete")
    public R delete(@RequestBody String[] userids);

}
