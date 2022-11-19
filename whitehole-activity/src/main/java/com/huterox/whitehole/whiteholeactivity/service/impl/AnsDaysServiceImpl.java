package com.huterox.whitehole.whiteholeactivity.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeactivity.dao.AnsDaysDao;
import com.huterox.whitehole.whiteholeactivity.entity.AnsDaysEntity;
import com.huterox.whitehole.whiteholeactivity.service.AnsDaysService;


@Service("ansDaysService")
public class AnsDaysServiceImpl extends ServiceImpl<AnsDaysDao, AnsDaysEntity> implements AnsDaysService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AnsDaysEntity> page = this.page(
                new Query<AnsDaysEntity>().getPage(params),
                new QueryWrapper<AnsDaysEntity>()
        );

        return new PageUtils(page);
    }

}