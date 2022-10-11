package com.huterox.whitehole.whiteholeblog.controller.base;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.huterox.whiteholecould.entity.blog.CommunityBelongEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huterox.whitehole.whiteholeblog.service.base.CommunityBelongService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:21:46
 */
@RestController
@RequestMapping("whiteholeblog/communitybelong")
public class CommunityBelongController {
    @Autowired
    private CommunityBelongService communityBelongService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholeblog:communitybelong:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = communityBelongService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("whiteholeblog:communitybelong:info")
    public R info(@PathVariable("userid") String userid){
		CommunityBelongEntity communityBelong = communityBelongService.getById(userid);

        return R.ok().put("communityBelong", communityBelong);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholeblog:communitybelong:save")
    public R save(@RequestBody CommunityBelongEntity communityBelong){
		communityBelongService.save(communityBelong);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholeblog:communitybelong:update")
    public R update(@RequestBody CommunityBelongEntity communityBelong){
		communityBelongService.updateById(communityBelong);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholeblog:communitybelong:delete")
    public R delete(@RequestBody String[] userids){
		communityBelongService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
