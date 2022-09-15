package com.huterox.whitehole.whiteholeuser.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeuser.dao.LogBaseDao;
import com.huterox.whitehole.whiteholeuser.entity.LogBaseEntity;
import com.huterox.whitehole.whiteholeuser.service.LogBaseService;


@Service("logBaseService")
public class LogBaseServiceImpl extends ServiceImpl<LogBaseDao, LogBaseEntity> implements LogBaseService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LogBaseEntity> page = this.page(
                new Query<LogBaseEntity>().getPage(params),
                new QueryWrapper<LogBaseEntity>()
        );

        return new PageUtils(page);
    }

}