package com.huterox.whitehole.whiteholequiz.service.base.impl;

import com.huterox.whiteholecould.entity.quiz.AnsComComentEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholequiz.dao.AnsComComentDao;
import com.huterox.whitehole.whiteholequiz.service.base.AnsComComentService;


@Service("ansComComentService")
public class AnsComComentServiceImpl extends ServiceImpl<AnsComComentDao, AnsComComentEntity> implements AnsComComentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AnsComComentEntity> page = this.page(
                new Query<AnsComComentEntity>().getPage(params),
                new QueryWrapper<AnsComComentEntity>()
        );

        return new PageUtils(page);
    }

}