package com.huterox.whitehole.whiteholeuser.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeuser.dao.LogForkDao;
import com.huterox.whitehole.whiteholeuser.entity.LogForkEntity;
import com.huterox.whitehole.whiteholeuser.service.LogForkService;


@Service("logForkService")
public class LogForkServiceImpl extends ServiceImpl<LogForkDao, LogForkEntity> implements LogForkService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LogForkEntity> page = this.page(
                new Query<LogForkEntity>().getPage(params),
                new QueryWrapper<LogForkEntity>()
        );

        return new PageUtils(page);
    }

}