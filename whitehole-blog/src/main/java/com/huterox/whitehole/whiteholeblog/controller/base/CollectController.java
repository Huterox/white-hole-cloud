package com.huterox.whitehole.whiteholeblog.controller.base;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.huterox.whiteholecould.entity.blog.CollectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huterox.whitehole.whiteholeblog.service.base.CollectService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 这个是问题的收藏，我们来看看是谁收藏了这个问题
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:21:46
 */
@RestController
@RequestMapping("whiteholeblog/collect")
public class CollectController {
    @Autowired
    private CollectService collectService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholeblog:collect:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = collectService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("whiteholeblog:collect:info")
    public R info(@PathVariable("userid") String userid){
		CollectEntity collect = collectService.getById(userid);

        return R.ok().put("collect", collect);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholeblog:collect:save")
    public R save(@RequestBody CollectEntity collect){
		collectService.save(collect);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholeblog:collect:update")
    public R update(@RequestBody CollectEntity collect){
		collectService.updateById(collect);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholeblog:collect:delete")
    public R delete(@RequestBody String[] userids){
		collectService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
