package com.huterox.whitehole.whiteholeuser.service.base.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeuser.dao.CommunityJoinDao;
import com.huterox.whitehole.whiteholeuser.entity.base.CommunityJoinEntity;
import com.huterox.whitehole.whiteholeuser.service.base.CommunityJoinService;


@Service("communityJoinService")
public class CommunityJoinServiceImpl extends ServiceImpl<CommunityJoinDao, CommunityJoinEntity> implements CommunityJoinService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CommunityJoinEntity> page = this.page(
                new Query<CommunityJoinEntity>().getPage(params),
                new QueryWrapper<CommunityJoinEntity>()
        );

        return new PageUtils(page);
    }

}