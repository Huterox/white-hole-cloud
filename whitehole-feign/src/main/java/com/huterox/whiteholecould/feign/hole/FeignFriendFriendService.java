package com.huterox.whiteholecould.feign.hole;


import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.hole.FriendFriendEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 这个是用户的好友列表，由于服务器的成本问题，我们的聊天和消息通知服务都是被动式的。
这个表式双向的，某一方删除，双方的信息都会删除。为了安全这边也是需要校验码的。
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:37:20
 */
@FeignClient("hole")
@RequestMapping("whiteholehole/friendfriend")
public interface FeignFriendFriendService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholehole:friendfriend:list")
    public R list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/info/{myUserid}")
    //RequiresPermissions("whiteholehole:friendfriend:info")
    public R info(@PathVariable("myUserid") String myUserid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholehole:friendfriend:save")
    public R save(@RequestBody FriendFriendEntity friendFriend);
    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholehole:friendfriend:update")
    public R update(@RequestBody FriendFriendEntity friendFriend);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholehole:friendfriend:delete")
    public R delete(@RequestBody String[] myUserids);
}
