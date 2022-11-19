package com.huterox.whitehole.whiteholeuser.controller.base;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.huterox.whiteholecould.entity.user.LogManageCommunityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.huterox.whitehole.whiteholeuser.service.base.LogManageCommunityService;
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
@RequestMapping("user/logmanagecommunity")
public class LogManageCommunityController {
    @Autowired
    private LogManageCommunityService logManageCommunityService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:logmanagecommunity:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = logManageCommunityService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:logmanagecommunity:info")
    public R info(@PathVariable("userid") String userid){
		LogManageCommunityEntity logManageCommunity = logManageCommunityService.getById(userid);

        return R.ok().put("logManageCommunity", logManageCommunity);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:logmanagecommunity:save")
    public R save(@RequestBody LogManageCommunityEntity logManageCommunity){
		logManageCommunityService.save(logManageCommunity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:logmanagecommunity:update")
    public R update(@RequestBody LogManageCommunityEntity logManageCommunity){
		logManageCommunityService.updateById(logManageCommunity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:logmanagecommunity:delete")
    public R delete(@RequestBody String[] userids){
		logManageCommunityService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
