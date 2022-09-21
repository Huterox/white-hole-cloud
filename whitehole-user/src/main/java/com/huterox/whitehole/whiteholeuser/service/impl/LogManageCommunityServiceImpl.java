package com.huterox.whitehole.whiteholeuser.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeuser.dao.LogManageCommunityDao;
import com.huterox.whitehole.whiteholeuser.entity.LogManageCommunityEntity;
import com.huterox.whitehole.whiteholeuser.service.LogManageCommunityService;


@Service("logManageCommunityService")
public class LogManageCommunityServiceImpl extends ServiceImpl<LogManageCommunityDao, LogManageCommunityEntity> implements LogManageCommunityService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String key = (String) params.get("key");
        IPage<LogManageCommunityEntity> page_params = new Query<LogManageCommunityEntity>().getPage(params);
        QueryWrapper<LogManageCommunityEntity> logManageCommunityEntityQueryWrapper = new QueryWrapper<>();
        if(key!=null){
            logManageCommunityEntityQueryWrapper.like("userid",key).or().
                    like("communityid",key).or().
                    like("community_name",key);
        }
        IPage<LogManageCommunityEntity> page = this.page(
                page_params,
                logManageCommunityEntityQueryWrapper
        );

        return new PageUtils(page);
    }

}