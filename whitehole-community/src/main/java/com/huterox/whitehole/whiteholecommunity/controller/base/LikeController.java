package com.huterox.whitehole.whiteholecommunity.controller.base;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.huterox.whiteholecould.entity.community.LikeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huterox.whitehole.whiteholecommunity.service.base.LikeService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:34:12
 */
@RestController
@RequestMapping("whiteholecommunity/like")
public class LikeController {
    @Autowired
    private LikeService likeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholecommunity:like:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = likeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{communityid}")
    //RequiresPermissions("whiteholecommunity:like:info")
    public R info(@PathVariable("communityid") Long communityid){
		LikeEntity like = likeService.getById(communityid);

        return R.ok().put("like", like);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholecommunity:like:save")
    public R save(@RequestBody LikeEntity like){
		likeService.save(like);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholecommunity:like:update")
    public R update(@RequestBody LikeEntity like){
		likeService.updateById(like);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholecommunity:like:delete")
    public R delete(@RequestBody Long[] communityids){
		likeService.removeByIds(Arrays.asList(communityids));

        return R.ok();
    }

}
