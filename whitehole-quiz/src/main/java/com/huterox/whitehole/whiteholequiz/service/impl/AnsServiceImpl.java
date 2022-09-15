package com.huterox.whitehole.whiteholequiz.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholequiz.dao.AnsDao;
import com.huterox.whitehole.whiteholequiz.entity.AnsEntity;
import com.huterox.whitehole.whiteholequiz.service.AnsService;


@Service("ansService")
public class AnsServiceImpl extends ServiceImpl<AnsDao, AnsEntity> implements AnsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AnsEntity> page = this.page(
                new Query<AnsEntity>().getPage(params),
                new QueryWrapper<AnsEntity>()
        );

        return new PageUtils(page);
    }

}