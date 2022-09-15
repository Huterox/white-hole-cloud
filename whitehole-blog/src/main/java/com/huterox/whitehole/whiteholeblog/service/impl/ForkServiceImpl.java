package com.huterox.whitehole.whiteholeblog.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeblog.dao.ForkDao;
import com.huterox.whitehole.whiteholeblog.entity.ForkEntity;
import com.huterox.whitehole.whiteholeblog.service.ForkService;


@Service("forkService")
public class ForkServiceImpl extends ServiceImpl<ForkDao, ForkEntity> implements ForkService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ForkEntity> page = this.page(
                new Query<ForkEntity>().getPage(params),
                new QueryWrapper<ForkEntity>()
        );

        return new PageUtils(page);
    }

}