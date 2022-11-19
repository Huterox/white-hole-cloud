package com.huterox.whitehole.whiteholeactivity.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeactivity.dao.ActivityDao;
import com.huterox.whitehole.whiteholeactivity.entity.ActivityEntity;
import com.huterox.whitehole.whiteholeactivity.service.ActivityService;


@Service("activityService")
public class ActivityServiceImpl extends ServiceImpl<ActivityDao, ActivityEntity> implements ActivityService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        IPage<ActivityEntity> page_params = new Query<ActivityEntity>().getPage(params);
        QueryWrapper<ActivityEntity> activityEntityQueryWrapper = new QueryWrapper<>();
        if(key!=null){
            activityEntityQueryWrapper.like("activityid",key).or().
                    like("activity_title",key).or().
                    like("actorid1",key).or().
                    like("actor1_nickname",key);
        }
        IPage<ActivityEntity> page = this.page(
                page_params,
                activityEntityQueryWrapper
        );

        return new PageUtils(page);
    }

}