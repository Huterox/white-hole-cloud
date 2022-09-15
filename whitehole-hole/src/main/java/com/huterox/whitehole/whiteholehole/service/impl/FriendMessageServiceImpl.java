package com.huterox.whitehole.whiteholehole.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholehole.dao.FriendMessageDao;
import com.huterox.whitehole.whiteholehole.entity.FriendMessageEntity;
import com.huterox.whitehole.whiteholehole.service.FriendMessageService;


@Service("friendMessageService")
public class FriendMessageServiceImpl extends ServiceImpl<FriendMessageDao, FriendMessageEntity> implements FriendMessageService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<FriendMessageEntity> page = this.page(
                new Query<FriendMessageEntity>().getPage(params),
                new QueryWrapper<FriendMessageEntity>()
        );

        return new PageUtils(page);
    }

}