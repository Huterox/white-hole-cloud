package com.huterox.whitehole.whiteholecommunity.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholecommunity.dao.BlogsDao;
import com.huterox.whitehole.whiteholecommunity.entity.BlogsEntity;
import com.huterox.whitehole.whiteholecommunity.service.BlogsService;


@Service("blogsService")
public class BlogsServiceImpl extends ServiceImpl<BlogsDao, BlogsEntity> implements BlogsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BlogsEntity> page = this.page(
                new Query<BlogsEntity>().getPage(params),
                new QueryWrapper<BlogsEntity>()
        );

        return new PageUtils(page);
    }

}