package com.huterox.whitehole.whiteholehole.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholehole.dao.FriendFriendDao;
import com.huterox.whitehole.whiteholehole.entity.FriendFriendEntity;
import com.huterox.whitehole.whiteholehole.service.FriendFriendService;


@Service("friendFriendService")
public class FriendFriendServiceImpl extends ServiceImpl<FriendFriendDao, FriendFriendEntity> implements FriendFriendService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FriendFriendEntity> page = this.page(
                new Query<FriendFriendEntity>().getPage(params),
                new QueryWrapper<FriendFriendEntity>()
        );

        return new PageUtils(page);
    }

}