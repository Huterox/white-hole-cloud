package com.huterox.whiteholecould.feign.hole;

import com.huterox.common.utils.R;

import com.huterox.whiteholecould.entity.hole.FriendMessageEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:37:20
 */
@FeignClient("hole")
@RequestMapping("whiteholehole/friendmessage")
public interface FeignFriendMessageService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholehole:friendmessage:list")
    public R list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/info/{fromid}")
    //RequiresPermissions("whiteholehole:friendmessage:info")
    public R info(@PathVariable("fromid") String fromid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholehole:friendmessage:save")
    public R save(@RequestBody FriendMessageEntity friendMessage);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholehole:friendmessage:update")
    public R update(@RequestBody FriendMessageEntity friendMessage);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholehole:friendmessage:delete")
    public R delete(@RequestBody String[] fromids);

}
