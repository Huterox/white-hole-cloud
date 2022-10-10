package com.huterox.whitehole.whiteholeuser.service.base.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeuser.dao.HeadimgDao;
import com.huterox.whitehole.whiteholeuser.entity.base.HeadimgEntity;
import com.huterox.whitehole.whiteholeuser.service.base.HeadimgService;


@Service("headimgService")
public class HeadimgServiceImpl extends ServiceImpl<HeadimgDao, HeadimgEntity> implements HeadimgService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<HeadimgEntity> page = this.page(
                new Query<HeadimgEntity>().getPage(params),
                new QueryWrapper<HeadimgEntity>()
        );

        return new PageUtils(page);
    }

}