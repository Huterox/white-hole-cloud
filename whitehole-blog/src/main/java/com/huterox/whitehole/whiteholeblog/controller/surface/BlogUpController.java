package com.huterox.whitehole.whiteholeblog.controller.surface;

import com.huterox.common.holeAnnotation.NeedLogin;
import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholeblog.entity.surface.blogUp.UpBlogEntity;
import com.huterox.whitehole.whiteholeblog.service.surface.BlogUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog/up")
public class BlogUpController {

    @Autowired
    BlogUpService blogUpService;
    @PostMapping("/blogUp")
    @NeedLogin
    public R blogUp(@Validated @RequestBody UpBlogEntity upBlogEntity){
        return blogUpService.blogUp(upBlogEntity);
    }


}
