package com.huterox.whitehole.whiteholeactivity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huterox.common.utils.PageUtils;
import com.huterox.whitehole.whiteholeactivity.entity.BlogWritedayEntity;

import java.util.Map;

/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:34:46
 */
public interface BlogWritedayService extends IService<BlogWritedayEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

