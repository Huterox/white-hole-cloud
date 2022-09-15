package com.huterox.whitehole.whiteholeuser.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huterox.whitehole.whiteholeuser.entity.LogForkEntity;
import com.huterox.whitehole.whiteholeuser.service.LogForkService;
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
@RequestMapping("user/logfork")
public class LogForkController {
    @Autowired
    private LogForkService logForkService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:logfork:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = logForkService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:logfork:info")
    public R info(@PathVariable("userid") String userid){
		LogForkEntity logFork = logForkService.getById(userid);

        return R.ok().put("logFork", logFork);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:logfork:save")
    public R save(@RequestBody LogForkEntity logFork){
		logForkService.save(logFork);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:logfork:update")
    public R update(@RequestBody LogForkEntity logFork){
		logForkService.updateById(logFork);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:logfork:delete")
    public R delete(@RequestBody String[] userids){
		logForkService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
