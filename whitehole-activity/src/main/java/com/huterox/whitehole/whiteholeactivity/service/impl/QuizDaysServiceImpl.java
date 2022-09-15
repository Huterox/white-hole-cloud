package com.huterox.whitehole.whiteholeactivity.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeactivity.dao.QuizDaysDao;
import com.huterox.whitehole.whiteholeactivity.entity.QuizDaysEntity;
import com.huterox.whitehole.whiteholeactivity.service.QuizDaysService;


@Service("quizDaysService")
public class QuizDaysServiceImpl extends ServiceImpl<QuizDaysDao, QuizDaysEntity> implements QuizDaysService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<QuizDaysEntity> page = this.page(
                new Query<QuizDaysEntity>().getPage(params),
                new QueryWrapper<QuizDaysEntity>()
        );

        return new PageUtils(page);
    }

}