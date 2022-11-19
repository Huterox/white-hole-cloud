package com.huterox.whitehole.whiteholeactivity.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeactivity.dao.BlogWritedayDao;
import com.huterox.whitehole.whiteholeactivity.entity.BlogWritedayEntity;
import com.huterox.whitehole.whiteholeactivity.service.BlogWritedayService;


@Service("blogWritedayService")
public class BlogWritedayServiceImpl extends ServiceImpl<BlogWritedayDao, BlogWritedayEntity> implements BlogWritedayService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BlogWritedayEntity> page = this.page(
                new Query<BlogWritedayEntity>().getPage(params),
                new QueryWrapper<BlogWritedayEntity>()
        );

        return new PageUtils(page);
    }

}