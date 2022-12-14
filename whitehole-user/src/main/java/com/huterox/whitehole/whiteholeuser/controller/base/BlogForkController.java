package com.huterox.whitehole.whiteholeuser.controller.base;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huterox.whiteholecould.entity.user.BlogForkEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.huterox.whitehole.whiteholeuser.service.base.BlogForkService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@RestController
@RequestMapping("user/blogfork")
public class BlogForkController {
    @Autowired
    private BlogForkService blogForkService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:blogfork:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = blogForkService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:blogfork:info")
    public R info(@PathVariable("userid") String userid){
		BlogForkEntity blogFork = blogForkService.getById(userid);

        return R.ok().put("blogFork", blogFork);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:blogfork:save")
    public R save(@RequestBody BlogForkEntity blogFork){
		blogForkService.save(blogFork);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:blogfork:update")
    public R update(@RequestBody BlogForkEntity blogFork){
		blogForkService.updateById(blogFork);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:blogfork:delete")
    public R delete(@RequestBody String[] userids){
		blogForkService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

    @RequestMapping("/deletefork")
    //RequiresPermissions("user:blogfork:delete")
    public R deletefork(@RequestBody Map<String, Object> params){
        String userid = String.valueOf(params.get("userid"));
        Long articleid = Long.valueOf(String.valueOf(params.get("articleid")));
        QueryWrapper<BlogForkEntity> delWrapper = new QueryWrapper<>();
        delWrapper.eq("userid",userid)
                        .eq("articleid",articleid);
        blogForkService.remove(delWrapper);
        return R.ok();
    };

}
