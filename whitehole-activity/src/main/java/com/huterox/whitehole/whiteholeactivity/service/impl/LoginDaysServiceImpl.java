package com.huterox.whitehole.whiteholeactivity.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeactivity.dao.LoginDaysDao;
import com.huterox.whitehole.whiteholeactivity.entity.LoginDaysEntity;
import com.huterox.whitehole.whiteholeactivity.service.LoginDaysService;


@Service("loginDaysService")
public class LoginDaysServiceImpl extends ServiceImpl<LoginDaysDao, LoginDaysEntity> implements LoginDaysService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LoginDaysEntity> page = this.page(
                new Query<LoginDaysEntity>().getPage(params),
                new QueryWrapper<LoginDaysEntity>()
        );

        return new PageUtils(page);
    }

}