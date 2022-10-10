package com.huterox.whitehole.whiteholeuser.controller.base;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huterox.whitehole.whiteholeuser.entity.base.LogFriendEntity;
import com.huterox.whitehole.whiteholeuser.service.base.LogFriendService;
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
@RequestMapping("user/logfriend")
public class LogFriendController {
    @Autowired
    private LogFriendService logFriendService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:logfriend:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = logFriendService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:logfriend:info")
    public R info(@PathVariable("userid") String userid){
		LogFriendEntity logFriend = logFriendService.getById(userid);

        return R.ok().put("logFriend", logFriend);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:logfriend:save")
    public R save(@RequestBody LogFriendEntity logFriend){
		logFriendService.save(logFriend);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:logfriend:update")
    public R update(@RequestBody LogFriendEntity logFriend){
		logFriendService.updateById(logFriend);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:logfriend:delete")
    public R delete(@RequestBody String[] userids){
		logFriendService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
