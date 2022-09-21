package com.huterox.whitehole.whiteholeblog.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeblog.dao.BlogDao;
import com.huterox.whitehole.whiteholeblog.entity.BlogEntity;
import com.huterox.whitehole.whiteholeblog.service.BlogService;


@Service("blogService")
public class BlogServiceImpl extends ServiceImpl<BlogDao, BlogEntity> implements BlogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String key = (String) params.get("key");

        IPage<BlogEntity> page_params = new Query<BlogEntity>().getPage(params);
        QueryWrapper<BlogEntity> blogEntityQueryWrapper = new QueryWrapper<>();

        if(key!=null){
            blogEntityQueryWrapper.like("userid",key).or().
                    like("blogid",key).or().
                    like("user_nickname",key).or().
                    like("blog_title",key);
        }
        IPage<BlogEntity> page = this.page(
                page_params,
                blogEntityQueryWrapper
        );

        return new PageUtils(page);
    }

}