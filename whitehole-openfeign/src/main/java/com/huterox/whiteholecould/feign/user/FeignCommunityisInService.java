package com.huterox.whiteholecould.feign.user;


import com.huterox.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@FeignClient("user")
@RequestMapping("/user")
public interface FeignCommunityisInService {

    @RequestMapping("/userinfo/communityisIn")
    public R communityisIn(@RequestParam Map<String, Object> params);
}
