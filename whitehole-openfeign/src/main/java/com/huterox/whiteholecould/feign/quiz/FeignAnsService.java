package com.huterox.whiteholecould.feign.quiz;

import com.huterox.common.utils.R;

import com.huterox.whiteholecould.entity.quiz.AnsEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 问题的回答
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
@FeignClient("quiz")
@RequestMapping("whiteholequiz/ans")
public interface FeignAnsService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholequiz:ans:list")
    public R list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/info/{ansid}")
    //RequiresPermissions("whiteholequiz:ans:info")
    public R info(@PathVariable("ansid") Long ansid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholequiz:ans:save")
    public R save(@RequestBody AnsEntity ans);
    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholequiz:ans:update")
    public R update(@RequestBody AnsEntity ans);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholequiz:ans:delete")
    public R delete(@RequestBody Long[] ansids);

}
