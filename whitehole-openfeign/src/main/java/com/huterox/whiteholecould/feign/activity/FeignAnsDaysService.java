package com.huterox.whiteholecould.feign.activity;

import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;

import com.huterox.whiteholecould.entity.activity.AnsDaysEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:34:46
 */
@FeignClient("activity")
@RequestMapping("whiteholeactivity/ansdays")
public interface FeignAnsDaysService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholeactivity:ansdays:list")
    public R list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("whiteholeactivity:ansdays:info")
    public R info(@PathVariable("userid") String userid);
    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholeactivity:ansdays:save")
    public R save(@RequestBody AnsDaysEntity ansDays);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholeactivity:ansdays:update")
    public R update(@RequestBody AnsDaysEntity ansDays);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholeactivity:ansdays:delete")
    public R delete(@RequestBody String[] userids);
}
