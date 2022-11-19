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

import com.huterox.whitehole.whiteholeactivity.entity.AnsDaysEntity;
import com.huterox.whitehole.whiteholeactivity.service.AnsDaysService;
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
@RequestMapping("whiteholeactivity/ansdays")
public class AnsDaysController {
    @Autowired
    private AnsDaysService ansDaysService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholeactivity:ansdays:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = ansDaysService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("whiteholeactivity:ansdays:info")
    public R info(@PathVariable("userid") String userid){
		AnsDaysEntity ansDays = ansDaysService.getById(userid);

        return R.ok().put("ansDays", ansDays);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholeactivity:ansdays:save")
    public R save(@RequestBody AnsDaysEntity ansDays){
		ansDaysService.save(ansDays);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholeactivity:ansdays:update")
    public R update(@RequestBody AnsDaysEntity ansDays){
		ansDaysService.updateById(ansDays);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholeactivity:ansdays:delete")
    public R delete(@RequestBody String[] userids){
		ansDaysService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
