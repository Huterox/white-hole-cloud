package com.huterox.whitehole.whiteholeuser.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeuser.dao.LogAnsDao;
import com.huterox.whitehole.whiteholeuser.entity.LogAnsEntity;
import com.huterox.whitehole.whiteholeuser.service.LogAnsService;


@Service("logAnsService")
public class LogAnsServiceImpl extends ServiceImpl<LogAnsDao, LogAnsEntity> implements LogAnsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LogAnsEntity> page = this.page(
                new Query<LogAnsEntity>().getPage(params),
                new QueryWrapper<LogAnsEntity>()
        );

        return new PageUtils(page);
    }

}