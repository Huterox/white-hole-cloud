package com.huterox.whitehole.whiteholequiz.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholequiz.dao.QuizDao;
import com.huterox.whitehole.whiteholequiz.entity.QuizEntity;
import com.huterox.whitehole.whiteholequiz.service.QuizService;


@Service("quizService")
public class QuizServiceImpl extends ServiceImpl<QuizDao, QuizEntity> implements QuizService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<QuizEntity> page = this.page(
                new Query<QuizEntity>().getPage(params),
                new QueryWrapper<QuizEntity>()
        );

        return new PageUtils(page);
    }

}