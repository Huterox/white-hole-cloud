package com.huterox.whitehole.whiteholeblog.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huterox.whitehole.whiteholeblog.entity.PullEntity;
import com.huterox.whitehole.whiteholeblog.service.PullService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:21:46
 */
@RestController
@RequestMapping("whiteholeblog/pull")
public class PullController {
    @Autowired
    private PullService pullService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholeblog:pull:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = pullService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("whiteholeblog:pull:info")
    public R info(@PathVariable("userid") String userid){
		PullEntity pull = pullService.getById(userid);

        return R.ok().put("pull", pull);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholeblog:pull:save")
    public R save(@RequestBody PullEntity pull){
		pullService.save(pull);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholeblog:pull:update")
    public R update(@RequestBody PullEntity pull){
		pullService.updateById(pull);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholeblog:pull:delete")
    public R delete(@RequestBody String[] userids){
		pullService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
