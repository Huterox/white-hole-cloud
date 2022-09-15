package com.huterox.whitehole.whiteholeblog.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeblog.dao.CommentLikeDao;
import com.huterox.whitehole.whiteholeblog.entity.CommentLikeEntity;
import com.huterox.whitehole.whiteholeblog.service.CommentLikeService;


@Service("commentLikeService")
public class CommentLikeServiceImpl extends ServiceImpl<CommentLikeDao, CommentLikeEntity> implements CommentLikeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CommentLikeEntity> page = this.page(
                new Query<CommentLikeEntity>().getPage(params),
                new QueryWrapper<CommentLikeEntity>()
        );

        return new PageUtils(page);
    }

}