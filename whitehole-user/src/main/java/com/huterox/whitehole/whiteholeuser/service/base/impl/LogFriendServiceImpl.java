package com.huterox.whitehole.whiteholeuser.service.base.impl;

import com.huterox.whiteholecould.entity.user.LogFriendEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeuser.dao.LogFriendDao;
import com.huterox.whitehole.whiteholeuser.service.base.LogFriendService;


@Service("logFriendService")
public class LogFriendServiceImpl extends ServiceImpl<LogFriendDao, LogFriendEntity> implements LogFriendService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String key = (String) params.get("key");
        IPage<LogFriendEntity> page_params = new Query<LogFriendEntity>().getPage(params);
        QueryWrapper<LogFriendEntity> logFriendEntityQueryWrapper = new QueryWrapper<>();
        if(key!=null){
            logFriendEntityQueryWrapper.like("userid",key).or().
                    like("friendid",key).or().
                    like("friend_nickname",key);
        }
        IPage<LogFriendEntity> page = this.page(
                page_params,
                logFriendEntityQueryWrapper
        );

        return new PageUtils(page);
    }

}