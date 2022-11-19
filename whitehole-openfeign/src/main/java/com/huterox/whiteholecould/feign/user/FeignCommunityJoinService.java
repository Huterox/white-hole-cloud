package com.huterox.whiteholecould.feign.user;

import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.user.CommunityJoinEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@FeignClient("user")
@RequestMapping("user/communityjoin")
public interface FeignCommunityJoinService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:communityjoin:list")
    public R list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:communityjoin:info")
    public R info(@PathVariable("userid") String userid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:communityjoin:save")
    public R save(@RequestBody CommunityJoinEntity communityJoin);
    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:communityjoin:update")
    public R update(@RequestBody CommunityJoinEntity communityJoin);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:communityjoin:delete")
    public R delete(@RequestBody String[] userids);

}
