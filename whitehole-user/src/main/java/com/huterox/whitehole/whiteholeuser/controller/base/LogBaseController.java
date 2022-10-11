package com.huterox.whitehole.whiteholeuser.controller.base;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.huterox.whiteholecould.entity.user.LogBaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.huterox.whitehole.whiteholeuser.service.base.LogBaseService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 用户的一些日志信息
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@RestController
@RequestMapping("user/logbase")
public class LogBaseController {
    @Autowired
    private LogBaseService logBaseService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:logbase:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = logBaseService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:logbase:info")
    public R info(@PathVariable("userid") String userid){
		LogBaseEntity logBase = logBaseService.getById(userid);

        return R.ok().put("logBase", logBase);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:logbase:save")
    public R save(@RequestBody LogBaseEntity logBase){
		logBaseService.save(logBase);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:logbase:update")
    public R update(@RequestBody LogBaseEntity logBase){
		logBaseService.updateById(logBase);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:logbase:delete")
    public R delete(@RequestBody String[] userids){
		logBaseService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
