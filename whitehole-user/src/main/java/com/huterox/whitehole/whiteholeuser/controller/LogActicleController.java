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

import com.huterox.whitehole.whiteholeuser.entity.LogActicleEntity;
import com.huterox.whitehole.whiteholeuser.service.LogActicleService;
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
@RequestMapping("user/logacticle")
public class LogActicleController {
    @Autowired
    private LogActicleService logActicleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:logacticle:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = logActicleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:logacticle:info")
    public R info(@PathVariable("userid") String userid){
		LogActicleEntity logActicle = logActicleService.getById(userid);

        return R.ok().put("logActicle", logActicle);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:logacticle:save")
    public R save(@RequestBody LogActicleEntity logActicle){
		logActicleService.save(logActicle);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:logacticle:update")
    public R update(@RequestBody LogActicleEntity logActicle){
		logActicleService.updateById(logActicle);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:logacticle:delete")
    public R delete(@RequestBody String[] userids){
		logActicleService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
