package com.huterox.whiteholecould.feign.community;

import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.community.Q.AuthenticationAnsQ;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("community")
@RequestMapping("/community/up")
public interface FeignAuthenticationAnsService {
    @PostMapping("/authenticationAnsQ")
    public R AuthenticationAnsQ(AuthenticationAnsQ upAnsQ);
}
