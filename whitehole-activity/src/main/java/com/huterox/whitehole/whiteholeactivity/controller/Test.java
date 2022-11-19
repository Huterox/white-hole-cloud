package com.huterox.whitehole.whiteholeactivity.controller;


import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholeactivity.feign.BlogFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RefreshScope
@RestController
public class Test {

    @Autowired
    BlogFeignService blogFeignService;

    @RequestMapping("/testfeign")
    public R test(){
        Map<String, Object> map = new HashMap<>();
        R list = blogFeignService.list(map);
        return list;
    }
//    @Value("${activity.name}")
//    private String name;
//
//    @RequestMapping("/testapp")
//    public R testapp(){
//        return R.ok().put("name",name);
//    }

}
