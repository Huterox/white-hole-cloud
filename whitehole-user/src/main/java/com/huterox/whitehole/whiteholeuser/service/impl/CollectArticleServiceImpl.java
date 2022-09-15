package com.huterox.whitehole.whiteholeuser.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeuser.dao.CollectArticleDao;
import com.huterox.whitehole.whiteholeuser.entity.CollectArticleEntity;
import com.huterox.whitehole.whiteholeuser.service.CollectArticleService;


@Service("collectArticleService")
public class CollectArticleServiceImpl extends ServiceImpl<CollectArticleDao, CollectArticleEntity> implements CollectArticleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CollectArticleEntity> page = this.page(
                new Query<CollectArticleEntity>().getPage(params),
                new QueryWrapper<CollectArticleEntity>()
        );

        return new PageUtils(page);
    }

}