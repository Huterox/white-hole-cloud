package com.huterox.whiteholecould.feign.user;

import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.user.FriendEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 这个是用户的好友列表，由于服务器的成本问题，我们的聊天和消息通知服务都是被动式的。
这个表式双向的，某一方删除，双方的信息都会删除。为了安全这边也是需要校验码的。
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:08
 */
@FeignClient("user")
@RequestMapping("user/friend")
public interface FeignFriendService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:friend:list")
    public R list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/info/{myUserid}")
    //RequiresPermissions("user:friend:info")
    public R info(@PathVariable("myUserid") String myUserid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:friend:save")
    public R save(@RequestBody FriendEntity friend);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:friend:update")
    public R update(@RequestBody FriendEntity friend);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:friend:delete")
    public R delete(@RequestBody String[] myUserids);

}
