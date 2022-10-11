package com.huterox.whitehole.whiteholeuser.service.base.impl;

import com.huterox.whiteholecould.entity.user.LogForkEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeuser.dao.LogForkDao;
import com.huterox.whitehole.whiteholeuser.service.base.LogForkService;


@Service("logForkService")
public class LogForkServiceImpl extends ServiceImpl<LogForkDao, LogForkEntity> implements LogForkService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String key = (String) params.get("key");
        IPage<LogForkEntity> page_params = new Query<LogForkEntity>().getPage(params);
        QueryWrapper<LogForkEntity> logForkEntityQueryWrapper = new QueryWrapper<>();
        if(key!=null){
            logForkEntityQueryWrapper.like("userid",key).or().
                    like("articleid",key).or().
                    like("article_title",key);
        }

        IPage<LogForkEntity> page = this.page(
                page_params,
                logForkEntityQueryWrapper
        );

        return new PageUtils(page);
    }

}