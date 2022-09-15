package com.huterox.whitehole.whiteholeblog.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeblog.dao.ComComentDao;
import com.huterox.whitehole.whiteholeblog.entity.ComComentEntity;
import com.huterox.whitehole.whiteholeblog.service.ComComentService;


@Service("comComentService")
public class ComComentServiceImpl extends ServiceImpl<ComComentDao, ComComentEntity> implements ComComentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ComComentEntity> page = this.page(
                new Query<ComComentEntity>().getPage(params),
                new QueryWrapper<ComComentEntity>()
        );

        return new PageUtils(page);
    }

}