package com.huterox.whitehole.whiteholeuser.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeuser.dao.FriendDao;
import com.huterox.whitehole.whiteholeuser.entity.FriendEntity;
import com.huterox.whitehole.whiteholeuser.service.FriendService;


@Service("friendService")
public class FriendServiceImpl extends ServiceImpl<FriendDao, FriendEntity> implements FriendService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FriendEntity> page = this.page(
                new Query<FriendEntity>().getPage(params),
                new QueryWrapper<FriendEntity>()
        );

        return new PageUtils(page);
    }

}