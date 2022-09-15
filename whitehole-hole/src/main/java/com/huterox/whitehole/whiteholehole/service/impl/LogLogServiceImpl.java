package com.huterox.whitehole.whiteholehole.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholehole.dao.LogLogDao;
import com.huterox.whitehole.whiteholehole.entity.LogLogEntity;
import com.huterox.whitehole.whiteholehole.service.LogLogService;


@Service("logLogService")
public class LogLogServiceImpl extends ServiceImpl<LogLogDao, LogLogEntity> implements LogLogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LogLogEntity> page = this.page(
                new Query<LogLogEntity>().getPage(params),
                new QueryWrapper<LogLogEntity>()
        );

        return new PageUtils(page);
    }

}