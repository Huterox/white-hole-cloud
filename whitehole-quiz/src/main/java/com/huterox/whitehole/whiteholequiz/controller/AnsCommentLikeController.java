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

import com.huterox.whitehole.whiteholequiz.entity.AnsCommentLikeEntity;
import com.huterox.whitehole.whiteholequiz.service.AnsCommentLikeService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
@RestController
@RequestMapping("whiteholequiz/anscommentlike")
public class AnsCommentLikeController {
    @Autowired
    private AnsCommentLikeService ansCommentLikeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholequiz:anscommentlike:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = ansCommentLikeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("whiteholequiz:anscommentlike:info")
    public R info(@PathVariable("userid") String userid){
		AnsCommentLikeEntity ansCommentLike = ansCommentLikeService.getById(userid);

        return R.ok().put("ansCommentLike", ansCommentLike);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholequiz:anscommentlike:save")
    public R save(@RequestBody AnsCommentLikeEntity ansCommentLike){
		ansCommentLikeService.save(ansCommentLike);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholequiz:anscommentlike:update")
    public R update(@RequestBody AnsCommentLikeEntity ansCommentLike){
		ansCommentLikeService.updateById(ansCommentLike);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholequiz:anscommentlike:delete")
    public R delete(@RequestBody String[] userids){
		ansCommentLikeService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
