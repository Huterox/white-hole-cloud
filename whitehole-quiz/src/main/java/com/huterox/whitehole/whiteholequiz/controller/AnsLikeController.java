package com.huterox.whitehole.whiteholequiz.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huterox.whitehole.whiteholequiz.entity.AnsLikeEntity;
import com.huterox.whitehole.whiteholequiz.service.AnsLikeService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 这个是回答的一个喜欢的记录
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
@RestController
@RequestMapping("whiteholequiz/anslike")
public class AnsLikeController {
    @Autowired
    private AnsLikeService ansLikeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholequiz:anslike:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = ansLikeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("whiteholequiz:anslike:info")
    public R info(@PathVariable("userid") String userid){
		AnsLikeEntity ansLike = ansLikeService.getById(userid);

        return R.ok().put("ansLike", ansLike);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholequiz:anslike:save")
    public R save(@RequestBody AnsLikeEntity ansLike){
		ansLikeService.save(ansLike);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholequiz:anslike:update")
    public R update(@RequestBody AnsLikeEntity ansLike){
		ansLikeService.updateById(ansLike);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholequiz:anslike:delete")
    public R delete(@RequestBody String[] userids){
		ansLikeService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
