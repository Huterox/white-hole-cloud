package com.huterox.whitehole.whiteholehole.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huterox.whitehole.whiteholehole.entity.FriendFriendEntity;
import com.huterox.whitehole.whiteholehole.service.FriendFriendService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 这个是用户的好友列表，由于服务器的成本问题，我们的聊天和消息通知服务都是被动式的。
这个表式双向的，某一方删除，双方的信息都会删除。为了安全这边也是需要校验码的。
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:37:20
 */
@RestController
@RequestMapping("whiteholehole/friendfriend")
public class FriendFriendController {
    @Autowired
    private FriendFriendService friendFriendService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholehole:friendfriend:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = friendFriendService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{myUserid}")
    //RequiresPermissions("whiteholehole:friendfriend:info")
    public R info(@PathVariable("myUserid") String myUserid){
		FriendFriendEntity friendFriend = friendFriendService.getById(myUserid);

        return R.ok().put("friendFriend", friendFriend);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholehole:friendfriend:save")
    public R save(@RequestBody FriendFriendEntity friendFriend){
		friendFriendService.save(friendFriend);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholehole:friendfriend:update")
    public R update(@RequestBody FriendFriendEntity friendFriend){
		friendFriendService.updateById(friendFriend);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholehole:friendfriend:delete")
    public R delete(@RequestBody String[] myUserids){
		friendFriendService.removeByIds(Arrays.asList(myUserids));

        return R.ok();
    }

}
