package com.huterox.whitehole.whiteholeuser.service.base.impl;

import com.huterox.whiteholecould.entity.user.LabelQuizLikeEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeuser.dao.LabelQuizLikeDao;
import com.huterox.whitehole.whiteholeuser.service.base.LabelQuizLikeService;


@Service("labelQuizLikeService")
public class LabelQuizLikeServiceImpl extends ServiceImpl<LabelQuizLikeDao, LabelQuizLikeEntity> implements LabelQuizLikeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LabelQuizLikeEntity> page = this.page(
                new Query<LabelQuizLikeEntity>().getPage(params),
                new QueryWrapper<LabelQuizLikeEntity>()
        );

        return new PageUtils(page);
    }

}