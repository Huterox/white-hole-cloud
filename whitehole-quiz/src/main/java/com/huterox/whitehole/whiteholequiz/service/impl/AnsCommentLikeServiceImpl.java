package com.huterox.whitehole.whiteholequiz.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholequiz.dao.AnsCommentLikeDao;
import com.huterox.whitehole.whiteholequiz.entity.AnsCommentLikeEntity;
import com.huterox.whitehole.whiteholequiz.service.AnsCommentLikeService;


@Service("ansCommentLikeService")
public class AnsCommentLikeServiceImpl extends ServiceImpl<AnsCommentLikeDao, AnsCommentLikeEntity> implements AnsCommentLikeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AnsCommentLikeEntity> page = this.page(
                new Query<AnsCommentLikeEntity>().getPage(params),
                new QueryWrapper<AnsCommentLikeEntity>()
        );

        return new PageUtils(page);
    }

}