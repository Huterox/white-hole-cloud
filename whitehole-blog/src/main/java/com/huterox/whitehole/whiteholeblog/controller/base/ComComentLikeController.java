package com.huterox.whitehole.whiteholeblog.controller.base;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.huterox.whiteholecould.entity.blog.ComComentLikeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huterox.whitehole.whiteholeblog.service.base.ComComentLikeService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 这个表就是单纯的记录一下谁给这个在回答的下面的评论当中的评论点赞了（准确的说是评论的回复）
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:21:46
 */
@RestController
@RequestMapping("whiteholeblog/comcomentlike")
public class ComComentLikeController {
    @Autowired
    private ComComentLikeService comComentLikeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholeblog:comcomentlike:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = comComentLikeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("whiteholeblog:comcomentlike:info")
    public R info(@PathVariable("userid") String userid){
		ComComentLikeEntity comComentLike = comComentLikeService.getById(userid);

        return R.ok().put("comComentLike", comComentLike);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholeblog:comcomentlike:save")
    public R save(@RequestBody ComComentLikeEntity comComentLike){
		comComentLikeService.save(comComentLike);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholeblog:comcomentlike:update")
    public R update(@RequestBody ComComentLikeEntity comComentLike){
		comComentLikeService.updateById(comComentLike);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholeblog:comcomentlike:delete")
    public R delete(@RequestBody String[] userids){
		comComentLikeService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
