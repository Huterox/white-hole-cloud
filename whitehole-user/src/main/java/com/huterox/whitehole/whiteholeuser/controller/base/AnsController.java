package com.huterox.whitehole.whiteholeuser.controller.base;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.huterox.whiteholecould.entity.user.AnsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.huterox.whitehole.whiteholeuser.service.base.AnsService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 这个是用户的回答。



 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@RestController
@RequestMapping("user/ans")
public class AnsController {
    @Autowired
    private AnsService ansService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:ans:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = ansService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:ans:info")
    public R info(@PathVariable("userid") String userid){
		AnsEntity ans = ansService.getById(userid);

        return R.ok().put("ans", ans);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:ans:save")
    public R save(@RequestBody AnsEntity ans){
		ansService.save(ans);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:ans:update")
    public R update(@RequestBody AnsEntity ans){
		ansService.updateById(ans);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:ans:delete")
    public R delete(@RequestBody String[] userids){
		ansService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
