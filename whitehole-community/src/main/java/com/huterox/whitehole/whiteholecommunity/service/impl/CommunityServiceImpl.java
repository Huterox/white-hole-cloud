package com.huterox.whitehole.whiteholecommunity.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholecommunity.dao.CommunityDao;
import com.huterox.whitehole.whiteholecommunity.entity.CommunityEntity;
import com.huterox.whitehole.whiteholecommunity.service.CommunityService;


@Service("communityService")
public class CommunityServiceImpl extends ServiceImpl<CommunityDao, CommunityEntity> implements CommunityService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CommunityEntity> page = this.page(
                new Query<CommunityEntity>().getPage(params),
                new QueryWrapper<CommunityEntity>()
        );

        return new PageUtils(page);
    }

}