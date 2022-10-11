package com.huterox.whitehole.whiteholeuser.controller.base;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.huterox.whiteholecould.entity.user.ManageCommunityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.huterox.whitehole.whiteholeuser.service.base.ManageCommunityService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 这个是用户创建的社区
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@RestController
@RequestMapping("user/managecommunity")
public class ManageCommunityController {
    @Autowired
    private ManageCommunityService manageCommunityService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:managecommunity:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = manageCommunityService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:managecommunity:info")
    public R info(@PathVariable("userid") String userid){
		ManageCommunityEntity manageCommunity = manageCommunityService.getById(userid);

        return R.ok().put("manageCommunity", manageCommunity);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:managecommunity:save")
    public R save(@RequestBody ManageCommunityEntity manageCommunity){
		manageCommunityService.save(manageCommunity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:managecommunity:update")
    public R update(@RequestBody ManageCommunityEntity manageCommunity){
		manageCommunityService.updateById(manageCommunity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:managecommunity:delete")
    public R delete(@RequestBody String[] userids){
		manageCommunityService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
