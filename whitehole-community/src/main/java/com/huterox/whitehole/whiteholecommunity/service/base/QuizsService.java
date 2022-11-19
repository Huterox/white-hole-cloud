package com.huterox.whitehole.whiteholecommunity.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huterox.common.utils.PageUtils;
import com.huterox.whiteholecould.entity.community.QuizsEntity;


import java.util.Map;

public interface QuizsService extends IService<QuizsEntity> {

    PageUtils queryPage(Map<String, Object> params) throws Exception;
}

