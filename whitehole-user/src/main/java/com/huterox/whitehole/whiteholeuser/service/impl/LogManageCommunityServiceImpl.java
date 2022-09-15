package com.huterox.whitehole.whiteholeuser.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeuser.dao.LogManageCommunityDao;
import com.huterox.whitehole.whiteholeuser.entity.LogManageCommunityEntity;
import com.huterox.whitehole.whiteholeuser.service.LogManageCommunityService;


@Service("logManageCommunityService")
public class LogManageCommunityServiceImpl extends ServiceImpl<LogManageCommunityDao, LogManageCommunityEntity> implements LogManageCommunityService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LogManageCommunityEntity> page = this.page(
                new Query<LogManageCommunityEntity>().getPage(params),
                new QueryWrapper<LogManageCommunityEntity>()
        );

        return new PageUtils(page);
    }

}