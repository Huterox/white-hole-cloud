package com.huterox.whitehole.whiteholeuser.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeuser.dao.LogActivityDao;
import com.huterox.whitehole.whiteholeuser.entity.LogActivityEntity;
import com.huterox.whitehole.whiteholeuser.service.LogActivityService;


@Service("logActivityService")
public class LogActivityServiceImpl extends ServiceImpl<LogActivityDao, LogActivityEntity> implements LogActivityService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LogActivityEntity> page = this.page(
                new Query<LogActivityEntity>().getPage(params),
                new QueryWrapper<LogActivityEntity>()
        );

        return new PageUtils(page);
    }

}