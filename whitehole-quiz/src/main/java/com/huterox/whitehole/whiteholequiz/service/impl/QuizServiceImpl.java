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
        String key = (String) params.get("key");
        IPage<QuizEntity> page_params = new Query<QuizEntity>().getPage(params);
        QueryWrapper<QuizEntity> quizEntityQueryWrapper = new QueryWrapper<>();
        if(key!=null){
            quizEntityQueryWrapper.like("userid",key).or().
                    like("quizid",key).or().
                    like("quiz_title",key).or().
                    like("user_nickname",key);
        }
        IPage<QuizEntity> page = this.page(
                page_params,
                quizEntityQueryWrapper
        );

        return new PageUtils(page);
    }

}