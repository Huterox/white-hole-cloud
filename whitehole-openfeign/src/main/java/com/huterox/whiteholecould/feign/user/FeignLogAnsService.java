package com.huterox.whiteholecould.feign.user;

import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.user.LogAnsEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@FeignClient("user")
@RequestMapping("user/logans")
public interface FeignLogAnsService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:logans:list")
    public R list(@RequestParam Map<String, Object> params);

    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:logans:info")
    public R info(@PathVariable("userid") String userid);
    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:logans:save")
    public R save(@RequestBody LogAnsEntity logAns);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:logans:update")
    public R update(@RequestBody LogAnsEntity logAns);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:logans:delete")
    public R delete(@RequestBody String[] userids);

}
