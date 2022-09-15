package com.huterox.whitehole.whiteholeblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huterox.common.utils.PageUtils;
import com.huterox.whitehole.whiteholeblog.entity.PullEntity;

import java.util.Map;

/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:21:46
 */
public interface PullService extends IService<PullEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
