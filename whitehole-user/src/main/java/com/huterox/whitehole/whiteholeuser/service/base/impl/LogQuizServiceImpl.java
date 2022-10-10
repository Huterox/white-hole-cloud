package com.huterox.whitehole.whiteholeuser.service.base.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeuser.dao.LogQuizDao;
import com.huterox.whitehole.whiteholeuser.entity.base.LogQuizEntity;
import com.huterox.whitehole.whiteholeuser.service.base.LogQuizService;


@Service("logQuizService")
public class LogQuizServiceImpl extends ServiceImpl<LogQuizDao, LogQuizEntity> implements LogQuizService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        IPage<LogQuizEntity> page_params = new Query<LogQuizEntity>().getPage(params);
        QueryWrapper<LogQuizEntity> logQuizEntityQueryWrapper = new QueryWrapper<>();
        if(key!=null){
            logQuizEntityQueryWrapper.like("userid",key).or().
                    like("quizid",key).or().
                    like("quiz_title",key);
        }
        IPage<LogQuizEntity> page = this.page(
                page_params,
                logQuizEntityQueryWrapper
        );

        return new PageUtils(page);
    }

}