package com.huterox.whitehole.whiteholeuser.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huterox.whitehole.whiteholeuser.entity.CollectArticleEntity;
import com.huterox.whitehole.whiteholeuser.service.CollectArticleService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 这个表是用来存储用户的收藏的博文的。这边是多了一个该文章的作者，减少了博文的修改时间。
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@RestController
@RequestMapping("user/collectarticle")
public class CollectArticleController {
    @Autowired
    private CollectArticleService collectArticleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:collectarticle:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = collectArticleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:collectarticle:info")
    public R info(@PathVariable("userid") String userid){
		CollectArticleEntity collectArticle = collectArticleService.getById(userid);

        return R.ok().put("collectArticle", collectArticle);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:collectarticle:save")
    public R save(@RequestBody CollectArticleEntity collectArticle){
		collectArticleService.save(collectArticle);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:collectarticle:update")
    public R update(@RequestBody CollectArticleEntity collectArticle){
		collectArticleService.updateById(collectArticle);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:collectarticle:delete")
    public R delete(@RequestBody String[] userids){
		collectArticleService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
