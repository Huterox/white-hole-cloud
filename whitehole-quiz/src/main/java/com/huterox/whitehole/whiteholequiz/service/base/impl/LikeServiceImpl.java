package com.huterox.whitehole.whiteholequiz.service.base.impl;

import com.huterox.whiteholecould.entity.quiz.LikeEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholequiz.dao.LikeDao;
import com.huterox.whitehole.whiteholequiz.service.base.LikeService;


@Service("likeService")
public class LikeServiceImpl extends ServiceImpl<LikeDao, LikeEntity> implements LikeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LikeEntity> page = this.page(
                new Query<LikeEntity>().getPage(params),
                new QueryWrapper<LikeEntity>()
        );

        return new PageUtils(page);
    }

}