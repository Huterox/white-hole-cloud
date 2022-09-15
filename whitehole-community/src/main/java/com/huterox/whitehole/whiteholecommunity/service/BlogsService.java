package com.huterox.whitehole.whiteholecommunity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huterox.common.utils.PageUtils;
import com.huterox.whitehole.whiteholecommunity.entity.BlogsEntity;

import java.util.Map;

/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:34:12
 */
public interface BlogsService extends IService<BlogsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

