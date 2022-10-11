package com.huterox.whitehole.whiteholequiz.service.base.impl;

import com.huterox.whiteholecould.entity.quiz.AnsCommentEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholequiz.dao.AnsCommentDao;
import com.huterox.whitehole.whiteholequiz.service.base.AnsCommentService;


@Service("ansCommentService")
public class AnsCommentServiceImpl extends ServiceImpl<AnsCommentDao, AnsCommentEntity> implements AnsCommentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AnsCommentEntity> page = this.page(
                new Query<AnsCommentEntity>().getPage(params),
                new QueryWrapper<AnsCommentEntity>()
        );

        return new PageUtils(page);
    }

}