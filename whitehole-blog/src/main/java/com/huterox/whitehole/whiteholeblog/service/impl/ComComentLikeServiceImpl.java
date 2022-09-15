package com.huterox.whitehole.whiteholeblog.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeblog.dao.ComComentLikeDao;
import com.huterox.whitehole.whiteholeblog.entity.ComComentLikeEntity;
import com.huterox.whitehole.whiteholeblog.service.ComComentLikeService;


@Service("comComentLikeService")
public class ComComentLikeServiceImpl extends ServiceImpl<ComComentLikeDao, ComComentLikeEntity> implements ComComentLikeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ComComentLikeEntity> page = this.page(
                new Query<ComComentLikeEntity>().getPage(params),
                new QueryWrapper<ComComentLikeEntity>()
        );

        return new PageUtils(page);
    }

}