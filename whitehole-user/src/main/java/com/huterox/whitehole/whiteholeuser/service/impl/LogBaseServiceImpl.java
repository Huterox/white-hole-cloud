package com.huterox.whitehole.whiteholeuser.service.impl;

import com.huterox.whitehole.whiteholeuser.entity.UserEntity;
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

        String key = (String) params.get("key");
        IPage<LogBaseEntity> page_params = new Query<LogBaseEntity>().getPage(params);
        QueryWrapper<LogBaseEntity> LogBaseEntityQueryWrapper = new QueryWrapper<>();

        if(key!=null){
            LogBaseEntityQueryWrapper.like("userid",key);
        }

        IPage<LogBaseEntity> page = this.page(
                page_params,
                LogBaseEntityQueryWrapper
        );

        return new PageUtils(page);
    }

}