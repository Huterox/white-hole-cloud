package com.huterox.whitehole.whiteholequiz.service.surface.baseUpQuiz.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huterox.common.utils.DateUtils;
import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholequiz.dao.QuizDao;
import com.huterox.whitehole.whiteholequiz.entity.surface.baseUpQuiz.UpQuizEntity;
import com.huterox.whitehole.whiteholequiz.exception.BizCodeEnum;
import com.huterox.whitehole.whiteholequiz.service.base.QuizService;
import com.huterox.whitehole.whiteholequiz.service.surface.baseUpQuiz.BaseUpQuizService;
import com.huterox.whiteholecould.entity.quiz.QuizEntity;
import com.huterox.whiteholecould.entity.user.HeadimgEntity;
import com.huterox.whiteholecould.entity.user.LogQuizEntity;
import com.huterox.whiteholecould.entity.user.UserEntity;
import com.huterox.whiteholecould.feign.user.FeignHeadimgService;
import com.huterox.whiteholecould.feign.user.FeignLogQuizService;

import com.huterox.whiteholecould.feign.user.FeignUserService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseUpQuizServiceImpl implements BaseUpQuizService {

    @Autowired
    FeignUserService feignUserService;

    @Autowired
    FeignHeadimgService feignHeadimgService;

    @Autowired
    QuizService quizService;

    @Autowired
    FeignLogQuizService feignLogQuizService;

    @Override
    public R BaseUpQuiz(UpQuizEntity entity) {

        String userid = entity.getUserid();
        //判断用户是否存在
        R info = feignUserService.info(userid);
        UserEntity user = (UserEntity) info.get("user");
        if(user!=null){
            //这里的话我们需要开启分布式事务，但是现在目前我们没有去做集成
            QuizEntity quizEntity = new QuizEntity();
            BeanUtils.copyProperties(entity,quizEntity);
            quizEntity.setCreateTime(DateUtils.getCurrentTime());
            quizEntity.setUserNickname(user.getNickname());
            /**
             * 查看用户的头像，这里的话我们还是做同步的
             * */
            HeadimgEntity headimg = (HeadimgEntity) feignHeadimgService.headimg(userid).get("headimg");
            if(headimg!=null){
                quizEntity.setUserImg(headimg.getImgpath());
            }
            quizService.save(quizEntity);

            /**
             * 更新用户的操作日志
             * */
            LogQuizEntity logQuizEntity = new LogQuizEntity();
            logQuizEntity.setQuizid(quizEntity.getQuizid());
            logQuizEntity.setQuizTitle(quizEntity.getQuizTitle());
            logQuizEntity.setCreatTime(quizEntity.getCreateTime());
            logQuizEntity.setUserid(quizEntity.getUserid());
            logQuizEntity.setAction(1);
            feignLogQuizService.save(logQuizEntity);

        }else {
            return R.error(BizCodeEnum.NO_SUCHUSER.getCode(),BizCodeEnum.NO_SUCHUSER.getMsg());
        }

        return R.ok();
    }
}
