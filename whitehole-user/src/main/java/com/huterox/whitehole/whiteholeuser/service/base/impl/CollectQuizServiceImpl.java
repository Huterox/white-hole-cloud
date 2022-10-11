package com.huterox.whitehole.whiteholeuser.service.base.impl;

import com.huterox.whiteholecould.entity.user.CollectQuizEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeuser.dao.CollectQuizDao;
import com.huterox.whitehole.whiteholeuser.service.base.CollectQuizService;


@Service("collectQuizService")
public class CollectQuizServiceImpl extends ServiceImpl<CollectQuizDao, CollectQuizEntity> implements CollectQuizService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CollectQuizEntity> page = this.page(
                new Query<CollectQuizEntity>().getPage(params),
                new QueryWrapper<CollectQuizEntity>()
        );

        return new PageUtils(page);
    }

}