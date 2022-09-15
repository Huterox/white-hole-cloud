package com.huterox.whitehole.whiteholeblog.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeblog.dao.PullDao;
import com.huterox.whitehole.whiteholeblog.entity.PullEntity;
import com.huterox.whitehole.whiteholeblog.service.PullService;


@Service("pullService")
public class PullServiceImpl extends ServiceImpl<PullDao, PullEntity> implements PullService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PullEntity> page = this.page(
                new Query<PullEntity>().getPage(params),
                new QueryWrapper<PullEntity>()
        );

        return new PageUtils(page);
    }

}