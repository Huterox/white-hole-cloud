package com.huterox.whitehole.whiteholeuser.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeuser.dao.LogFriendDao;
import com.huterox.whitehole.whiteholeuser.entity.LogFriendEntity;
import com.huterox.whitehole.whiteholeuser.service.LogFriendService;


@Service("logFriendService")
public class LogFriendServiceImpl extends ServiceImpl<LogFriendDao, LogFriendEntity> implements LogFriendService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LogFriendEntity> page = this.page(
                new Query<LogFriendEntity>().getPage(params),
                new QueryWrapper<LogFriendEntity>()
        );

        return new PageUtils(page);
    }

}