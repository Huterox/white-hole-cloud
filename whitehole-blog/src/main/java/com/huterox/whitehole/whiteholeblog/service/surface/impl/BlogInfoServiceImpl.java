package com.huterox.whitehole.whiteholeblog.service.surface.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholeblog.entity.surface.blogInfo.BlogBody;
import com.huterox.whitehole.whiteholeblog.entity.surface.blogInfo.QueryStatusWithBlog;
import com.huterox.whitehole.whiteholeblog.entity.surface.blogInfo.StatusWithBlogEntity;
import com.huterox.whitehole.whiteholeblog.exception.BizCodeEnum;
import com.huterox.whitehole.whiteholeblog.service.base.BlogService;
import com.huterox.whitehole.whiteholeblog.service.base.ContentService;
import com.huterox.whitehole.whiteholeblog.service.base.ForkService;
import com.huterox.whitehole.whiteholeblog.service.surface.BlogInfoService;
import com.huterox.whitehole.whiteholeblog.utils.BlogType;
import com.huterox.whiteholecould.entity.blog.BlogEntity;
import com.huterox.whiteholecould.entity.blog.ContentEntity;
import com.huterox.whiteholecould.entity.blog.ForkEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogInfoServiceImpl implements BlogInfoService {

    @Autowired
    BlogService blogService;
    @Autowired
    ContentService contentService;
    @Autowired
    ForkService forkService;

    @Override
    public R blogById(Long blogById) {
        BlogEntity blog = blogService.getById(blogById);
        ContentEntity content = contentService.getOne(new QueryWrapper<ContentEntity>().eq("blogid", blog.getBlogid())
                .orderByDesc("contentid")
                .last("limit 0,1")
        );
        if(blog==null){
            return R.error(BizCodeEnum.NO_SUCHBLOG.getCode(),BizCodeEnum.NO_SUCHBLOG.getMsg());
        }
        if(content==null){
            return R.error(BizCodeEnum.NO_SUCHCONTENT.getCode(), BizCodeEnum.NO_SUCHCONTENT.getMsg());
        }
        BlogBody blogBody = new BlogBody();
        BeanUtils.copyProperties(blog,blogBody);
        blogBody.setContent(content.getContent());
        blogBody.setBlogtype(BlogType.BLOGTYPE.get(blog.getBlogtype()));
        return R.ok().put("blog",blogBody);
    }

    @Override
    public R statusWithblog(QueryStatusWithBlog entity) {
        //这个玩意主要是用来查看这个当前的博文和这个访问的用户的关系的
        //比如这个用户有没有fork这篇文章，有没有点赞，这样的话给用户一个判断
        //现在的话我们只有一个，就是判断这个用户有没有fork
        QueryWrapper<ForkEntity> hasquery = new QueryWrapper<>();
        hasquery.eq("userid", entity.getUserid())
                .eq("blogid", entity.getBlogid());
        ForkEntity one = forkService.getOne(hasquery);
        StatusWithBlogEntity statusWithBlogEntity = new StatusWithBlogEntity();
        if(one!=null){
            statusWithBlogEntity.setIsfork(true);
        }
        return R.ok().put("status",statusWithBlogEntity);

    }
}
