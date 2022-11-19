package com.huterox.whiteholecould.feign.blog;


import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.blog.Q.CommunityBlogUpQ;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("blog")
@RequestMapping("/blog/up")
public interface FeignCommunityBlogUp {


    @PostMapping("/communityBlogUp")
    public R communityBlogUp(CommunityBlogUpQ entity);
}
