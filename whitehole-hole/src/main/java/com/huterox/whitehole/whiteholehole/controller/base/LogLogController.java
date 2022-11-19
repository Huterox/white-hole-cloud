package com.huterox.whitehole.whiteholehole.controller.base;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.huterox.whiteholecould.entity.hole.LogLogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.huterox.whitehole.whiteholehole.service.base.LogLogService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 目前的话，我们的树洞只有两种功能，所以的话，我们这边就按照功能进行建表。
现在这个表是关于用户日志的表。
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:37:20
 */
@RestController
@RequestMapping("whiteholehole/loglog")
public class LogLogController {
    @Autowired
    private LogLogService logLogService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholehole:loglog:list")
    public R list(@RequestParam Map<String, Object> params) throws Exception {
        PageUtils page = logLogService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("whiteholehole:loglog:info")
    public R info(@PathVariable("userid") String userid){
		LogLogEntity logLog = logLogService.getById(userid);

        return R.ok().put("logLog", logLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholehole:loglog:save")
    public R save(@RequestBody LogLogEntity logLog){
		logLogService.save(logLog);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholehole:loglog:update")
    public R update(@RequestBody LogLogEntity logLog){
		logLogService.updateById(logLog);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholehole:loglog:delete")
    public R delete(@RequestBody String[] userids){
		logLogService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
