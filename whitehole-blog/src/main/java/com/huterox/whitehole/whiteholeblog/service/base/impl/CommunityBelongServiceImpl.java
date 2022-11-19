package com.huterox.whitehole.whiteholeblog.service.base.impl;

import com.huterox.whiteholecould.entity.blog.CommunityBelongEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeblog.dao.CommunityBelongDao;

import com.huterox.whitehole.whiteholeblog.service.base.CommunityBelongService;


@Service("communityBelongService")
public class CommunityBelongServiceImpl extends ServiceImpl<CommunityBelongDao, CommunityBelongEntity> implements CommunityBelongService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CommunityBelongEntity> page = this.page(
                new Query<CommunityBelongEntity>().getPage(params),
                new QueryWrapper<CommunityBelongEntity>()
        );

        return new PageUtils(page);
    }

}