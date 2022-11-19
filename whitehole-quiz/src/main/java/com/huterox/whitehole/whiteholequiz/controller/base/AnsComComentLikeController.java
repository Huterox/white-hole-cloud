package com.huterox.whitehole.whiteholequiz.controller.base;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.huterox.whiteholecould.entity.quiz.AnsComComentLikeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huterox.whitehole.whiteholequiz.service.base.AnsComComentLikeService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 这个表就是单纯的记录一下谁给这个在回答的下面的评论当中的评论点赞了（准确的说是评论的回复）
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
@RestController
@RequestMapping("whiteholequiz/anscomcomentlike")
public class AnsComComentLikeController {
    @Autowired
    private AnsComComentLikeService ansComComentLikeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholequiz:anscomcomentlike:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = ansComComentLikeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("whiteholequiz:anscomcomentlike:info")
    public R info(@PathVariable("userid") String userid){
		AnsComComentLikeEntity ansComComentLike = ansComComentLikeService.getById(userid);

        return R.ok().put("ansComComentLike", ansComComentLike);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholequiz:anscomcomentlike:save")
    public R save(@RequestBody AnsComComentLikeEntity ansComComentLike){
		ansComComentLikeService.save(ansComComentLike);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholequiz:anscomcomentlike:update")
    public R update(@RequestBody AnsComComentLikeEntity ansComComentLike){
		ansComComentLikeService.updateById(ansComComentLike);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholequiz:anscomcomentlike:delete")
    public R delete(@RequestBody String[] userids){
		ansComComentLikeService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
