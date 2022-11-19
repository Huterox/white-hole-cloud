package com.huterox.whitehole.whiteholeactivity.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huterox.whitehole.whiteholeactivity.entity.LoginDaysEntity;
import com.huterox.whitehole.whiteholeactivity.service.LoginDaysService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:34:46
 */
@RestController
@RequestMapping("whiteholeactivity/logindays")
public class LoginDaysController {
    @Autowired
    private LoginDaysService loginDaysService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholeactivity:logindays:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = loginDaysService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("whiteholeactivity:logindays:info")
    public R info(@PathVariable("userid") String userid){
		LoginDaysEntity loginDays = loginDaysService.getById(userid);

        return R.ok().put("loginDays", loginDays);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholeactivity:logindays:save")
    public R save(@RequestBody LoginDaysEntity loginDays){
		loginDaysService.save(loginDays);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholeactivity:logindays:update")
    public R update(@RequestBody LoginDaysEntity loginDays){
		loginDaysService.updateById(loginDays);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholeactivity:logindays:delete")
    public R delete(@RequestBody String[] userids){
		loginDaysService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
