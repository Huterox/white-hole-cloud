package com.huterox.whitehole.whiteholeuser.service.base.impl;

import com.huterox.whiteholecould.entity.user.BlogForkEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeuser.dao.BlogForkDao;
import com.huterox.whitehole.whiteholeuser.service.base.BlogForkService;


@Service("blogForkService")
public class BlogForkServiceImpl extends ServiceImpl<BlogForkDao, BlogForkEntity> implements BlogForkService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BlogForkEntity> page = this.page(
                new Query<BlogForkEntity>().getPage(params),
                new QueryWrapper<BlogForkEntity>()
        );

        return new PageUtils(page);
    }

}