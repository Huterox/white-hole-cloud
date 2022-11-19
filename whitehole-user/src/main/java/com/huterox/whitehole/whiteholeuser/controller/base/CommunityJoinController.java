package com.huterox.whitehole.whiteholeuser.controller.base;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.huterox.whiteholecould.entity.user.CommunityJoinEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huterox.whitehole.whiteholeuser.service.base.CommunityJoinService;
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
@RequestMapping("user/communityjoin")
public class CommunityJoinController {
    @Autowired
    private CommunityJoinService communityJoinService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:communityjoin:list")
    public R list(@RequestParam Map<String, Object> params) throws Exception {
        PageUtils page = communityJoinService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:communityjoin:info")
    public R info(@PathVariable("userid") String userid){
		CommunityJoinEntity communityJoin = communityJoinService.getById(userid);

        return R.ok().put("communityJoin", communityJoin);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:communityjoin:save")
    public R save(@RequestBody CommunityJoinEntity communityJoin){
		communityJoinService.save(communityJoin);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:communityjoin:update")
    public R update(@RequestBody CommunityJoinEntity communityJoin){
		communityJoinService.updateById(communityJoin);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:communityjoin:delete")
    public R delete(@RequestBody String[] userids){
		communityJoinService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
