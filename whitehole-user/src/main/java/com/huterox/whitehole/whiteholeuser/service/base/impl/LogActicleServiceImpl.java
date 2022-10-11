package com.huterox.whitehole.whiteholeuser.service.base.impl;

import com.huterox.whiteholecould.entity.user.LogActicleEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeuser.dao.LogActicleDao;
import com.huterox.whitehole.whiteholeuser.service.base.LogActicleService;


@Service("logActicleService")
public class LogActicleServiceImpl extends ServiceImpl<LogActicleDao, LogActicleEntity> implements LogActicleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String key = ((String) params.get("key"));
        IPage<LogActicleEntity> page_params = new Query<LogActicleEntity>().getPage(params);
        QueryWrapper<LogActicleEntity> logActicleEntityQueryWrapper = new QueryWrapper<>();

        if(key!=null){
            logActicleEntityQueryWrapper.like("userid",key).or().
                    like("articleid",key);
        }



        IPage<LogActicleEntity> page = this.page(
                page_params,
                logActicleEntityQueryWrapper
        );

        return new PageUtils(page);
    }

}