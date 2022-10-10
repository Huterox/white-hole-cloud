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

import com.huterox.whitehole.whiteholeuser.entity.base.LogAnsEntity;
import com.huterox.whitehole.whiteholeuser.service.base.LogAnsService;
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
@RequestMapping("user/logans")
public class LogAnsController {
    @Autowired
    private LogAnsService logAnsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:logans:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = logAnsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:logans:info")
    public R info(@PathVariable("userid") String userid){
		LogAnsEntity logAns = logAnsService.getById(userid);

        return R.ok().put("logAns", logAns);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:logans:save")
    public R save(@RequestBody LogAnsEntity logAns){
		logAnsService.save(logAns);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:logans:update")
    public R update(@RequestBody LogAnsEntity logAns){
		logAnsService.updateById(logAns);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:logans:delete")
    public R delete(@RequestBody String[] userids){
		logAnsService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
