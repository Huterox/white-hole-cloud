package com.huterox.whitehole.whiteholeuser.service.base.impl;

import com.huterox.whiteholecould.entity.user.LabelBlogLikeEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeuser.dao.LabelBlogLikeDao;
import com.huterox.whitehole.whiteholeuser.service.base.LabelBlogLikeService;


@Service("labelBlogLikeService")
public class LabelBlogLikeServiceImpl extends ServiceImpl<LabelBlogLikeDao, LabelBlogLikeEntity> implements LabelBlogLikeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LabelBlogLikeEntity> page = this.page(
                new Query<LabelBlogLikeEntity>().getPage(params),
                new QueryWrapper<LabelBlogLikeEntity>()
        );

        return new PageUtils(page);
    }

}