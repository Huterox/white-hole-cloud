package com.huterox.whitehole.whiteholeuser.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeuser.dao.ManageCommunityDao;
import com.huterox.whitehole.whiteholeuser.entity.ManageCommunityEntity;
import com.huterox.whitehole.whiteholeuser.service.ManageCommunityService;


@Service("manageCommunityService")
public class ManageCommunityServiceImpl extends ServiceImpl<ManageCommunityDao, ManageCommunityEntity> implements ManageCommunityService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ManageCommunityEntity> page = this.page(
                new Query<ManageCommunityEntity>().getPage(params),
                new QueryWrapper<ManageCommunityEntity>()
        );

        return new PageUtils(page);
    }

}