package com.huterox.whitehole.whiteholequiz.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholequiz.dao.AnsComComentLikeDao;
import com.huterox.whitehole.whiteholequiz.entity.AnsComComentLikeEntity;
import com.huterox.whitehole.whiteholequiz.service.AnsComComentLikeService;


@Service("ansComComentLikeService")
public class AnsComComentLikeServiceImpl extends ServiceImpl<AnsComComentLikeDao, AnsComComentLikeEntity> implements AnsComComentLikeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AnsComComentLikeEntity> page = this.page(
                new Query<AnsComComentLikeEntity>().getPage(params),
                new QueryWrapper<AnsComComentLikeEntity>()
        );

        return new PageUtils(page);
    }

}