package com.huterox.whitehole.whiteholeblog.controller.surface;

import com.huterox.common.holeAnnotation.NeedLogin;
import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholeblog.entity.surface.fork.UpForkEntity;
import com.huterox.whitehole.whiteholeblog.service.surface.BlogForkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog/fork")
/**
 * 为了安全起见，这里的所有的请求都是POST
 * */
public class BlogForkController {

    @Autowired
    BlogForkService blogForkService;

    @PostMapping("/blogFork")
    @NeedLogin
    public R blogFork(@Validated @RequestBody UpForkEntity entity){
        return blogForkService.forkBlog(entity);
    }

    @PostMapping("/viewPull")
    public R viewPull(){
        return null;
    }

    @PostMapping("/acceptMerge")
    public R acceptMerge(){
        return null;
    }
}

