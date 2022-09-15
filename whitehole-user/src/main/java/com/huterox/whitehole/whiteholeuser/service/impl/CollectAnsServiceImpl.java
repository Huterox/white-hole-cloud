package com.huterox.whitehole.whiteholeuser.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeuser.dao.CollectAnsDao;
import com.huterox.whitehole.whiteholeuser.entity.CollectAnsEntity;
import com.huterox.whitehole.whiteholeuser.service.CollectAnsService;


@Service("collectAnsService")
public class CollectAnsServiceImpl extends ServiceImpl<CollectAnsDao, CollectAnsEntity> implements CollectAnsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CollectAnsEntity> page = this.page(
                new Query<CollectAnsEntity>().getPage(params),
                new QueryWrapper<CollectAnsEntity>()
        );

        return new PageUtils(page);
    }

}