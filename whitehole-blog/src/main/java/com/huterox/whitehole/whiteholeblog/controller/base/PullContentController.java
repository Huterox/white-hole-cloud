package com.huterox.whitehole.whiteholeblog.controller.base;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.huterox.whiteholecould.entity.blog.PullContentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huterox.whitehole.whiteholeblog.service.base.PullContentService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 当用户同意之后，那么博文对应的内容就会被覆盖，同时在blog的贡献表当中显示提交用户的信息！
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:21:46
 */
@RestController
@RequestMapping("whiteholeblog/pullcontent")
public class PullContentController {
    @Autowired
    private PullContentService pullContentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholeblog:pullcontent:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = pullContentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("whiteholeblog:pullcontent:info")
    public R info(@PathVariable("userid") String userid){
		PullContentEntity pullContent = pullContentService.getById(userid);

        return R.ok().put("pullContent", pullContent);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholeblog:pullcontent:save")
    public R save(@RequestBody PullContentEntity pullContent){
		pullContentService.save(pullContent);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholeblog:pullcontent:update")
    public R update(@RequestBody PullContentEntity pullContent){
		pullContentService.updateById(pullContent);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholeblog:pullcontent:delete")
    public R delete(@RequestBody String[] userids){
		pullContentService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
