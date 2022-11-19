package com.huterox.whitehole.whiteholeuser.controller.base;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.huterox.whiteholecould.entity.user.FriendEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.huterox.whitehole.whiteholeuser.service.base.FriendService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 这个是用户的好友列表，由于服务器的成本问题，我们的聊天和消息通知服务都是被动式的。
这个表式双向的，某一方删除，双方的信息都会删除。为了安全这边也是需要校验码的。
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:08
 */
@RestController
@RequestMapping("user/friend")
public class FriendController {
    @Autowired
    private FriendService friendService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:friend:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = friendService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{myUserid}")
    //RequiresPermissions("user:friend:info")
    public R info(@PathVariable("myUserid") String myUserid){
		FriendEntity friend = friendService.getById(myUserid);

        return R.ok().put("friend", friend);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:friend:save")
    public R save(@RequestBody FriendEntity friend){
		friendService.save(friend);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:friend:update")
    public R update(@RequestBody FriendEntity friend){
		friendService.updateById(friend);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:friend:delete")
    public R delete(@RequestBody String[] myUserids){
		friendService.removeByIds(Arrays.asList(myUserids));

        return R.ok();
    }

}
