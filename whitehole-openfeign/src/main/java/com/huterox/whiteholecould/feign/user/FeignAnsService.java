package com.huterox.whiteholecould.feign.user;

import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.user.AnsEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


/**
 * 这个是用户的回答。



 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@FeignClient("user")
@RequestMapping("user/ans")
public interface FeignAnsService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:ans:list")
    public R list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:ans:info")
    public R info(@PathVariable("userid") String userid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:ans:save")
    public R save(@RequestBody AnsEntity ans);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:ans:update")
    public R update(@RequestBody AnsEntity ans);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:ans:delete")
    public R delete(@RequestBody String[] userids);

}
