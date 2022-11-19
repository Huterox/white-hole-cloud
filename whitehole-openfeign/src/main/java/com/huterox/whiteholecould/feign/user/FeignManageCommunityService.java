package com.huterox.whiteholecould.feign.user;


import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.user.ManageCommunityEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


/**
 * 这个是用户创建的社区
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@FeignClient("user")
@RequestMapping("user/managecommunity")
public interface FeignManageCommunityService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:managecommunity:list")
    public R list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:managecommunity:info")
    public R info(@PathVariable("userid") String userid);
    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:managecommunity:save")
    public R save(@RequestBody ManageCommunityEntity manageCommunity);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:managecommunity:update")
    public R update(@RequestBody ManageCommunityEntity manageCommunity);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:managecommunity:delete")
    public R delete(@RequestBody String[] userids);
}
