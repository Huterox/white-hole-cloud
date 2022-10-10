package com.huterox.whitehole.whiteholeuser.service.base.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeuser.dao.AchievementDao;
import com.huterox.whitehole.whiteholeuser.entity.base.AchievementEntity;
import com.huterox.whitehole.whiteholeuser.service.base.AchievementService;


@Service("achievementService")
public class AchievementServiceImpl extends ServiceImpl<AchievementDao, AchievementEntity> implements AchievementService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AchievementEntity> page = this.page(
                new Query<AchievementEntity>().getPage(params),
                new QueryWrapper<AchievementEntity>()
        );

        return new PageUtils(page);
    }

}