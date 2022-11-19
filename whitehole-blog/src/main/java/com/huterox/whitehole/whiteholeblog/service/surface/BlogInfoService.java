package com.huterox.whitehole.whiteholeblog.service.surface;


import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholeblog.entity.surface.blogInfo.QueryStatusWithBlog;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

public interface BlogInfoService {
    public R blogById(Long blogByid);

    public R statusWithblog(QueryStatusWithBlog entity);

}
