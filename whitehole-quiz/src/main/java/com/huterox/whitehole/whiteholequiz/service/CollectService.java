package com.huterox.whitehole.whiteholequiz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huterox.common.utils.PageUtils;
import com.huterox.whitehole.whiteholequiz.entity.CollectEntity;

import java.util.Map;

/**
 * 这个是问题的收藏，我们来看看是谁收藏了这个问题
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
public interface CollectService extends IService<CollectEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

