package com.huterox.whitehole.whiteholeblog.service.base.impl;

import com.huterox.whiteholecould.entity.blog.ContributeEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeblog.dao.ContributeDao;

import com.huterox.whitehole.whiteholeblog.service.base.ContributeService;


@Service("contributeService")
public class ContributeServiceImpl extends ServiceImpl<ContributeDao, ContributeEntity> implements ContributeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ContributeEntity> page = this.page(
                new Query<ContributeEntity>().getPage(params),
                new QueryWrapper<ContributeEntity>()
        );

        return new PageUtils(page);
    }

}