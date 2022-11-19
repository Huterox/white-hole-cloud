package com.huterox.whitehole.whiteholecommunity.controller.base;

import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholecommunity.service.base.QuizsService;
import com.huterox.whiteholecould.entity.community.QuizsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("whiteholecommunity/quizs")
public class QuizsController {

    @Autowired
    private QuizsService quizsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholecommunity:quizs:list")
    public R list(@RequestParam Map<String, Object> params) throws Exception {
        PageUtils page = quizsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{communityid}")
    //RequiresPermissions("whiteholecommunity:quizs:info")
    public R info(@PathVariable("communityid") Long communityid){
        QuizsEntity quizs = quizsService.getById(communityid);

        return R.ok().put("quizs", quizs);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholecommunity:quizs:save")
    public R save(@RequestBody QuizsEntity quizs){
        quizsService.save(quizs);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholecommunity:quizs:update")
    public R update(@RequestBody QuizsEntity quizs){
        quizsService.updateById(quizs);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholecommunity:quizs:delete")
    public R delete(@RequestBody Long[] communityids){
        quizsService.removeByIds(Arrays.asList(communityids));
        return R.ok();
    }
}
