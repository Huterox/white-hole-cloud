package com.huterox.whitehole.whiteholequiz.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huterox.whitehole.whiteholequiz.entity.AnsCollectEntity;
import com.huterox.whitehole.whiteholequiz.service.AnsCollectService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 这个是问题回答的收藏，有哪些用户收藏了，我们的筛选标准和我们这个的问题的标准是一样的
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
@RestController
@RequestMapping("whiteholequiz/anscollect")
public class AnsCollectController {
    @Autowired
    private AnsCollectService ansCollectService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholequiz:anscollect:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = ansCollectService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("whiteholequiz:anscollect:info")
    public R info(@PathVariable("userid") String userid){
		AnsCollectEntity ansCollect = ansCollectService.getById(userid);

        return R.ok().put("ansCollect", ansCollect);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholequiz:anscollect:save")
    public R save(@RequestBody AnsCollectEntity ansCollect){
		ansCollectService.save(ansCollect);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholequiz:anscollect:update")
    public R update(@RequestBody AnsCollectEntity ansCollect){
		ansCollectService.updateById(ansCollect);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholequiz:anscollect:delete")
    public R delete(@RequestBody String[] userids){
		ansCollectService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
