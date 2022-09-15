package com.huterox.whitehole.whiteholeuser.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeuser.dao.LogQuizDao;
import com.huterox.whitehole.whiteholeuser.entity.LogQuizEntity;
import com.huterox.whitehole.whiteholeuser.service.LogQuizService;


@Service("logQuizService")
public class LogQuizServiceImpl extends ServiceImpl<LogQuizDao, LogQuizEntity> implements LogQuizService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LogQuizEntity> page = this.page(
                new Query<LogQuizEntity>().getPage(params),
                new QueryWrapper<LogQuizEntity>()
        );

        return new PageUtils(page);
    }

}