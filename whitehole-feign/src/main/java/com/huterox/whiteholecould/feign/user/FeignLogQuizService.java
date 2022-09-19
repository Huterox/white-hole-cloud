package com.huterox.whiteholecould.feign.user;


import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.user.LogQuizEntity;
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
@RequestMapping("user/logquiz")
public interface FeignLogQuizService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:logquiz:list")
    public R list(@RequestParam Map<String, Object> params);

    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:logquiz:info")
    public R info(@PathVariable("userid") String userid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:logquiz:save")
    public R save(@RequestBody LogQuizEntity logQuiz);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:logquiz:update")
    public R update(@RequestBody LogQuizEntity logQuiz);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:logquiz:delete")
    public R delete(@RequestBody String[] userids);

}
