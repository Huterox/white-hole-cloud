package com.huterox.whiteholecould.feign.user;


import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.user.CollectQuizEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


/**
 * 问题回答交流的本质还是博文的逻辑，但是区别是，问答模块具备强交流性。

用户可能收藏的是整个问题，包括问题当中的所有回答，也有可能是针对某一个问题的，其他用户的回答，因此这边还需要创建一个新的表。
专门用于存储用户收藏的问题。
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@FeignClient("user")
@RequestMapping("user/collectquiz")
public interface FeignCollectQuizService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:collectquiz:list")
    public R list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:collectquiz:info")
    public R info(@PathVariable("userid") String userid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:collectquiz:save")
    public R save(@RequestBody CollectQuizEntity collectQuiz);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:collectquiz:update")
    public R update(@RequestBody CollectQuizEntity collectQuiz);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:collectquiz:delete")
    public R delete(@RequestBody String[] userids);

}
