package com.huterox.whitehole.whiteholeblog.service.base.impl;

import com.huterox.whiteholecould.entity.blog.PullContentEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeblog.dao.PullContentDao;
import com.huterox.whitehole.whiteholeblog.service.base.PullContentService;


@Service("pullContentService")
public class PullContentServiceImpl extends ServiceImpl<PullContentDao, PullContentEntity> implements PullContentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PullContentEntity> page = this.page(
                new Query<PullContentEntity>().getPage(params),
                new QueryWrapper<PullContentEntity>()
        );

        return new PageUtils(page);
    }

}