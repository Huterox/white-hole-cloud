package com.huterox.whitehole.whiteholecommunity.controller.base;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.huterox.whiteholecould.entity.community.CommunityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huterox.whitehole.whiteholecommunity.service.base.CommunityService;
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
@RequestMapping("whiteholecommunity/community")
public class CommunityController {
    @Autowired
    private CommunityService communityService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholecommunity:community:list")
    public R list(@RequestParam Map<String, Object> params) throws Exception {
        PageUtils page = communityService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{communityid}")
    //RequiresPermissions("whiteholecommunity:community:info")
    public R info(@PathVariable("communityid") Long communityid){
		CommunityEntity community = communityService.getById(communityid);

        return R.ok().put("community", community);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholecommunity:community:save")
    public R save(@RequestBody CommunityEntity community){
		communityService.save(community);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholecommunity:community:update")
    public R update(@RequestBody CommunityEntity community){
		communityService.updateById(community);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholecommunity:community:delete")
    public R delete(@RequestBody Long[] communityids){
		communityService.removeByIds(Arrays.asList(communityids));

        return R.ok();
    }

}
