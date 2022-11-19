package com.huterox.whitehole.whiteholeactivity.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeactivity.dao.LogDao;
import com.huterox.whitehole.whiteholeactivity.entity.LogEntity;
import com.huterox.whitehole.whiteholeactivity.service.LogService;


@Service("logService")
public class LogServiceImpl extends ServiceImpl<LogDao, LogEntity> implements LogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        IPage<LogEntity> page_params = new Query<LogEntity>().getPage(params);
        QueryWrapper<LogEntity> logEntityQueryWrapper = new QueryWrapper<>();
        if(key!=null){
            logEntityQueryWrapper.like("actorid",key).or().
                    like("activityid",key);
        }
        IPage<LogEntity> page = this.page(
                page_params,
                logEntityQueryWrapper
        );

        return new PageUtils(page);
    }

}