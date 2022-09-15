package com.huterox.whitehole.whiteholequiz.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholequiz.dao.AnsCollectDao;
import com.huterox.whitehole.whiteholequiz.entity.AnsCollectEntity;
import com.huterox.whitehole.whiteholequiz.service.AnsCollectService;


@Service("ansCollectService")
public class AnsCollectServiceImpl extends ServiceImpl<AnsCollectDao, AnsCollectEntity> implements AnsCollectService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AnsCollectEntity> page = this.page(
                new Query<AnsCollectEntity>().getPage(params),
                new QueryWrapper<AnsCollectEntity>()
        );

        return new PageUtils(page);
    }

}