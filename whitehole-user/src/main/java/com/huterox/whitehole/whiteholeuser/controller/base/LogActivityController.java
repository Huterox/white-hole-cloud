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

import com.huterox.whitehole.whiteholeuser.entity.base.LogActivityEntity;
import com.huterox.whitehole.whiteholeuser.service.base.LogActivityService;
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
@RequestMapping("user/logactivity")
public class LogActivityController {
    @Autowired
    private LogActivityService logActivityService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:logactivity:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = logActivityService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:logactivity:info")
    public R info(@PathVariable("userid") String userid){
		LogActivityEntity logActivity = logActivityService.getById(userid);

        return R.ok().put("logActivity", logActivity);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:logactivity:save")
    public R save(@RequestBody LogActivityEntity logActivity){
		logActivityService.save(logActivity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:logactivity:update")
    public R update(@RequestBody LogActivityEntity logActivity){
		logActivityService.updateById(logActivity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:logactivity:delete")
    public R delete(@RequestBody String[] userids){
		logActivityService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
