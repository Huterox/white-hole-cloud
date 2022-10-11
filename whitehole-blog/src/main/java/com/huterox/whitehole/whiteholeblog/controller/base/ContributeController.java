package com.huterox.whitehole.whiteholeblog.controller.base;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.huterox.whiteholecould.entity.blog.ContributeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huterox.whitehole.whiteholeblog.service.base.ContributeService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 博客的贡献者列表 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:21:46
 */
@RestController
@RequestMapping("whiteholeblog/contribute")
public class ContributeController {
    @Autowired
    private ContributeService contributeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholeblog:contribute:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = contributeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("whiteholeblog:contribute:info")
    public R info(@PathVariable("userid") String userid){
		ContributeEntity contribute = contributeService.getById(userid);

        return R.ok().put("contribute", contribute);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholeblog:contribute:save")
    public R save(@RequestBody ContributeEntity contribute){
		contributeService.save(contribute);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholeblog:contribute:update")
    public R update(@RequestBody ContributeEntity contribute){
		contributeService.updateById(contribute);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholeblog:contribute:delete")
    public R delete(@RequestBody String[] userids){
		contributeService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
