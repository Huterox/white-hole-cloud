package com.huterox.whitehole.whiteholequiz.controller.base;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.huterox.whiteholecould.entity.quiz.AnsCommentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huterox.whitehole.whiteholequiz.service.base.AnsCommentService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 问题回答的评论
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
@RestController
@RequestMapping("whiteholequiz/anscomment")
public class AnsCommentController {
    @Autowired
    private AnsCommentService ansCommentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholequiz:anscomment:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = ansCommentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{commentid}")
    //RequiresPermissions("whiteholequiz:anscomment:info")
    public R info(@PathVariable("commentid") Long commentid){
		AnsCommentEntity ansComment = ansCommentService.getById(commentid);

        return R.ok().put("ansComment", ansComment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholequiz:anscomment:save")
    public R save(@RequestBody AnsCommentEntity ansComment){
		ansCommentService.save(ansComment);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholequiz:anscomment:update")
    public R update(@RequestBody AnsCommentEntity ansComment){
		ansCommentService.updateById(ansComment);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholequiz:anscomment:delete")
    public R delete(@RequestBody Long[] commentids){
		ansCommentService.removeByIds(Arrays.asList(commentids));

        return R.ok();
    }

}
