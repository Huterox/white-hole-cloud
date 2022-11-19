package com.huterox.whiteholecould.feign.quiz;


import com.huterox.common.utils.R;

import com.huterox.whiteholecould.entity.quiz.AnsCommentEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 问题回答的评论
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
@FeignClient("quiz")
@RequestMapping("whiteholequiz/anscomment")
public interface FeignAnsCommentService {


    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholequiz:anscomment:list")
    public R list(@RequestParam Map<String, Object> params);

    /**
     * 信息
     */
    @RequestMapping("/info/{commentid}")
    //RequiresPermissions("whiteholequiz:anscomment:info")
    public R info(@PathVariable("commentid") Long commentid);
    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholequiz:anscomment:save")
    public R save(@RequestBody AnsCommentEntity ansComment);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholequiz:anscomment:update")
    public R update(@RequestBody AnsCommentEntity ansComment);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholequiz:anscomment:delete")
    public R delete(@RequestBody Long[] commentids);

}
