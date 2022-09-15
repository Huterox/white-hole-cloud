package com.huterox.whitehole.whiteholeuser.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeuser.dao.LogActicleDao;
import com.huterox.whitehole.whiteholeuser.entity.LogActicleEntity;
import com.huterox.whitehole.whiteholeuser.service.LogActicleService;


@Service("logActicleService")
public class LogActicleServiceImpl extends ServiceImpl<LogActicleDao, LogActicleEntity> implements LogActicleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LogActicleEntity> page = this.page(
                new Query<LogActicleEntity>().getPage(params),
                new QueryWrapper<LogActicleEntity>()
        );

        return new PageUtils(page);
    }

}