package com.huterox.whiteholecould.feign.user;

import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.user.CollectArticleEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 这个表是用来存储用户的收藏的博文的。这边是多了一个该文章的作者，减少了博文的修改时间。
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@FeignClient("user")
@RequestMapping("user/collectarticle")
public interface FeignCollectArticleService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:collectarticle:list")
    public R list(@RequestParam Map<String, Object> params);

    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:collectarticle:info")
    public R info(@PathVariable("userid") String userid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:collectarticle:save")
    public R save(@RequestBody CollectArticleEntity collectArticle);
    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:collectarticle:update")
    public R update(@RequestBody CollectArticleEntity collectArticle);
    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:collectarticle:delete")
    public R delete(@RequestBody String[] userids);

}
