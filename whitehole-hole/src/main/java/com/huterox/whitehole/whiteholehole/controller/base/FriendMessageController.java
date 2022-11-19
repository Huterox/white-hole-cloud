package com.huterox.whitehole.whiteholehole.controller.base;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.huterox.whiteholecould.entity.hole.FriendMessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huterox.whitehole.whiteholehole.service.base.FriendMessageService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:37:20
 */
@RestController
@RequestMapping("whiteholehole/friendmessage")
public class FriendMessageController {
    @Autowired
    private FriendMessageService friendMessageService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholehole:friendmessage:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = friendMessageService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{fromid}")
    //RequiresPermissions("whiteholehole:friendmessage:info")
    public R info(@PathVariable("fromid") String fromid){
		FriendMessageEntity friendMessage = friendMessageService.getById(fromid);

        return R.ok().put("friendMessage", friendMessage);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholehole:friendmessage:save")
    public R save(@RequestBody FriendMessageEntity friendMessage){
		friendMessageService.save(friendMessage);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholehole:friendmessage:update")
    public R update(@RequestBody FriendMessageEntity friendMessage){
		friendMessageService.updateById(friendMessage);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholehole:friendmessage:delete")
    public R delete(@RequestBody String[] fromids){
		friendMessageService.removeByIds(Arrays.asList(fromids));

        return R.ok();
    }

}
