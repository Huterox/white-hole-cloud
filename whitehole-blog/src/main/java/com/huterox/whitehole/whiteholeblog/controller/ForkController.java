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

import com.huterox.whitehole.whiteholeblog.entity.ForkEntity;
import com.huterox.whitehole.whiteholeblog.service.ForkService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 文章被fork的状况
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:21:46
 */
@RestController
@RequestMapping("whiteholeblog/fork")
public class ForkController {
    @Autowired
    private ForkService forkService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholeblog:fork:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = forkService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("whiteholeblog:fork:info")
    public R info(@PathVariable("userid") String userid){
		ForkEntity fork = forkService.getById(userid);

        return R.ok().put("fork", fork);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholeblog:fork:save")
    public R save(@RequestBody ForkEntity fork){
		forkService.save(fork);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholeblog:fork:update")
    public R update(@RequestBody ForkEntity fork){
		forkService.updateById(fork);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholeblog:fork:delete")
    public R delete(@RequestBody String[] userids){
		forkService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
