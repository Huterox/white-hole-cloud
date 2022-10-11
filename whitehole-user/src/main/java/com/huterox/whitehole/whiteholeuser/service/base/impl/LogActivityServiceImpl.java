package com.huterox.whitehole.whiteholeuser.service.base.impl;

import com.huterox.whiteholecould.entity.user.LogActivityEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeuser.dao.LogActivityDao;
import com.huterox.whitehole.whiteholeuser.service.base.LogActivityService;


@Service("logActivityService")
public class LogActivityServiceImpl extends ServiceImpl<LogActivityDao, LogActivityEntity> implements LogActivityService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        IPage<LogActivityEntity> page_params = new Query<LogActivityEntity>().getPage(params);
        QueryWrapper<LogActivityEntity> logActivityEntityQueryWrapper = new QueryWrapper<>();
        if(key!=null){
            logActivityEntityQueryWrapper.like("userid",key).or().
                    like("activityid",key).or().
                    like("activitytitle",key);
        }
        IPage<LogActivityEntity> page = this.page(
            page_params,
            logActivityEntityQueryWrapper
        );
        return new PageUtils(page);
    }

}