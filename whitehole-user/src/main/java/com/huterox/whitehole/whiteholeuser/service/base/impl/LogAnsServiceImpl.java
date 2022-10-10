package com.huterox.whitehole.whiteholeuser.service.base.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeuser.dao.LogAnsDao;
import com.huterox.whitehole.whiteholeuser.entity.base.LogAnsEntity;
import com.huterox.whitehole.whiteholeuser.service.base.LogAnsService;


@Service("logAnsService")
public class LogAnsServiceImpl extends ServiceImpl<LogAnsDao, LogAnsEntity> implements LogAnsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        IPage<LogAnsEntity> page_params = new Query<LogAnsEntity>().getPage(params);
        QueryWrapper<LogAnsEntity> logAnsEntityQueryWrapper = new QueryWrapper<>();
        if(key!=null){
            logAnsEntityQueryWrapper.like("userid",key).or().
                    like("ansid",key).or().
                    like("quizid",key).or().
                    like("quiz_title",key);
        }

        IPage<LogAnsEntity> page = this.page(
            page_params,
            logAnsEntityQueryWrapper
        );

        return new PageUtils(page);
    }

}