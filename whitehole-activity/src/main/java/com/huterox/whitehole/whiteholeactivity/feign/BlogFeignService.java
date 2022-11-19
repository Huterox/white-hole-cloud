package com.huterox.whitehole.whiteholeactivity.feign;


import com.huterox.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient("blog")
@RequestMapping("whiteholeblog/blog")
public interface BlogFeignService {
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params);
}
