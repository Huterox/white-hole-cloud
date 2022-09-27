package com.huterox.whiteholecould.feign.community;

import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;

import com.huterox.whiteholecould.entity.community.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 加入到社区的用户
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:34:12
 */
@FeignClient("community")
@RequestMapping("whiteholecommunity/users")
public interface FeignUsersService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholecommunity:users:list")
    public R list(@RequestParam Map<String, Object> params);

    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("whiteholecommunity:users:info")
    public R info(@PathVariable("userid") String userid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholecommunity:users:save")
    public R save(@RequestBody UsersEntity users);
    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholecommunity:users:update")
    public R update(@RequestBody UsersEntity users);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholecommunity:users:delete")
    public R delete(@RequestBody String[] userids);
}
