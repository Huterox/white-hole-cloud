package com.huterox.whitehole.whiteholeblog.controller.surface;

import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholeblog.service.surface.BlogHomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog/home")
public class BlogHomePageController {

    @Autowired
    BlogHomePageService blogHomePageService;

    @RequestMapping("/hot")
    @Cacheable(value={"homePageBlog:hotHomePageList"},key = "#root.methodName")
    public R hotHomePageList() throws Exception {
        return blogHomePageService.hotBlogList();
    }
    @RequestMapping("/last")
    @Cacheable(value={"homePageBlog:lastHomePageList"},key = "#root.methodName")
    public R lastHomePageList() throws Exception {
        return blogHomePageService.lastBlogList();
    }
}
