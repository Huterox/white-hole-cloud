package com.huterox.whitehole.whiteholeblog.controller.surface;
import com.huterox.common.holeAnnotation.NeedLogin;
import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholeblog.entity.surface.blogInfo.QueryStatusWithBlog;
import com.huterox.whitehole.whiteholeblog.service.surface.BlogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog/bloginfo")
public class BlogInfoController {

    @Autowired
    BlogInfoService blogInfoService;

    @RequestMapping("/blogById")
    @Cacheable(value={"blogInfoController:blogById"},key = "#blogid")
    public R blogById(@RequestParam(value="blogid") Long blogid){
        //临时存储博文的信息，30分钟刷新一次（lazy）
        //在这里对博文的流量进行统计，先用本地锁，因为只有一个node，并且对一致性要求不高

        return blogInfoService.blogById(blogid);
    }

    @PostMapping("/status")
    @NeedLogin
    public R statusWithblog(@Validated @RequestBody QueryStatusWithBlog entity){
        return blogInfoService.statusWithblog(entity);
    }


}
