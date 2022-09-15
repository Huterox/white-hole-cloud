package com.huterox.whitehole.whiteholequiz.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholequiz.dao.AnsLikeDao;
import com.huterox.whitehole.whiteholequiz.entity.AnsLikeEntity;
import com.huterox.whitehole.whiteholequiz.service.AnsLikeService;


@Service("ansLikeService")
public class AnsLikeServiceImpl extends ServiceImpl<AnsLikeDao, AnsLikeEntity> implements AnsLikeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AnsLikeEntity> page = this.page(
                new Query<AnsLikeEntity>().getPage(params),
                new QueryWrapper<AnsLikeEntity>()
        );

        return new PageUtils(page);
    }

}