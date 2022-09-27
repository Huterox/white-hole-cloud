package com.huterox.whiteholecould.feign.user;


import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.user.MessageEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


/**
 * 这个表是用来记录用户消息的,关于消息的具体的一些内容，或者是详情页面
是由负责该消息，或者说产生这个消息的服务去处理，显示完整的消息。
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@FeignClient("user")
@RequestMapping("user/message")
public interface FeignMessageService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:message:list")
    public R list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:message:info")
    public R info(@PathVariable("userid") String userid);
    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:message:save")
    public R save(@RequestBody MessageEntity message);
    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:message:update")
    public R update(@RequestBody MessageEntity message);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:message:delete")
    public R delete(@RequestBody String[] userids);

}
