package com.huterox.whitehole.whiteholeuser.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huterox.whitehole.whiteholeuser.entity.LabelQuizLikeEntity;
import com.huterox.whitehole.whiteholeuser.service.LabelQuizLikeService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@RestController
@RequestMapping("user/labelquizlike")
public class LabelQuizLikeController {
    @Autowired
    private LabelQuizLikeService labelQuizLikeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:labelquizlike:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = labelQuizLikeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:labelquizlike:info")
    public R info(@PathVariable("userid") String userid){
		LabelQuizLikeEntity labelQuizLike = labelQuizLikeService.getById(userid);

        return R.ok().put("labelQuizLike", labelQuizLike);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:labelquizlike:save")
    public R save(@RequestBody LabelQuizLikeEntity labelQuizLike){
		labelQuizLikeService.save(labelQuizLike);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:labelquizlike:update")
    public R update(@RequestBody LabelQuizLikeEntity labelQuizLike){
		labelQuizLikeService.updateById(labelQuizLike);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:labelquizlike:delete")
    public R delete(@RequestBody String[] userids){
		labelQuizLikeService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
