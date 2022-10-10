package com.huterox.whitehole.whiteholeuser.controller.base;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huterox.whitehole.whiteholeuser.entity.base.CollectAnsEntity;
import com.huterox.whitehole.whiteholeuser.service.base.CollectAnsService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 这个就是用户收藏的问题的回答了，我们会直接定位到问题的具体的回答页面。
而，对于用户收藏的问题的话，我们是直接跳转到用户所收藏的问题页面。

由于这些玩意都是收藏的部分，所以的话，我们需要存储一个状态，我们默认状态都是1 也就是正常的，当访问具体的页面
的时候，做一个判断，如果说当前是status，他是异常的例如被删除或者下架了，那么再设置这个玩意为真实的status，用户可以选择删除这个玩意，玩意这个玩意又
正常了，失误删除就可惜了。



 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@RestController
@RequestMapping("user/collectans")
public class CollectAnsController {
    @Autowired
    private CollectAnsService collectAnsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:collectans:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = collectAnsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:collectans:info")
    public R info(@PathVariable("userid") String userid){
		CollectAnsEntity collectAns = collectAnsService.getById(userid);

        return R.ok().put("collectAns", collectAns);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:collectans:save")
    public R save(@RequestBody CollectAnsEntity collectAns){
		collectAnsService.save(collectAns);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:collectans:update")
    public R update(@RequestBody CollectAnsEntity collectAns){
		collectAnsService.updateById(collectAns);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:collectans:delete")
    public R delete(@RequestBody String[] userids){
		collectAnsService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
