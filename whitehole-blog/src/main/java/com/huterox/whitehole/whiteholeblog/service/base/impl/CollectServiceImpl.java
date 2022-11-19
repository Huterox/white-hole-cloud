package com.huterox.whitehole.whiteholeblog.service.base.impl;

import com.huterox.whiteholecould.entity.blog.CollectEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeblog.dao.CollectDao;

import com.huterox.whitehole.whiteholeblog.service.base.CollectService;


@Service("collectService")
public class CollectServiceImpl extends ServiceImpl<CollectDao, CollectEntity> implements CollectService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CollectEntity> page = this.page(
                new Query<CollectEntity>().getPage(params),
                new QueryWrapper<CollectEntity>()
        );

        return new PageUtils(page);
    }

}