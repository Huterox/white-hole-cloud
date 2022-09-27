package com.huterox.whiteholecould.feign.quiz;


import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.quiz.AnsLikeEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


/**
 * 这个是回答的一个喜欢的记录
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
@FeignClient("quiz")
@RequestMapping("whiteholequiz/anslike")
public interface FeignAnsLikeService {


    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholequiz:anslike:list")
    public R list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("whiteholequiz:anslike:info")
    public R info(@PathVariable("userid") String userid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholequiz:anslike:save")
    public R save(@RequestBody AnsLikeEntity ansLike);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholequiz:anslike:update")
    public R update(@RequestBody AnsLikeEntity ansLike);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholequiz:anslike:delete")
    public R delete(@RequestBody String[] userids);

}
