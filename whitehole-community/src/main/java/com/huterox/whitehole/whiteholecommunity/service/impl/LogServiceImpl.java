package com.huterox.whitehole.whiteholecommunity.service.impl;

import com.huterox.whitehole.whiteholecommunity.entity.CommunityEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholecommunity.dao.LogDao;
import com.huterox.whitehole.whiteholecommunity.entity.LogEntity;
import com.huterox.whitehole.whiteholecommunity.service.LogService;


@Service("logService")
public class LogServiceImpl extends ServiceImpl<LogDao, LogEntity> implements LogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        IPage<LogEntity> page_params = new Query<LogEntity>().getPage(params);
        QueryWrapper<LogEntity> LogEntityQueryWrapper = new QueryWrapper<>();
        if(key!=null){
            LogEntityQueryWrapper.like("communityid",key).or().
                    like("userid",key).or().
                    like("user_nickname",key);
        }
        IPage<LogEntity> page = this.page(
                page_params,
                LogEntityQueryWrapper
        );

        return new PageUtils(page);
    }

}