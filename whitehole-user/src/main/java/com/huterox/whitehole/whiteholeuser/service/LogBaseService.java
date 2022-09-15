package com.huterox.whitehole.whiteholeuser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huterox.common.utils.PageUtils;
import com.huterox.whitehole.whiteholeuser.entity.LogBaseEntity;

import java.util.Map;

/**
 * 用户的一些日志信息
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
public interface LogBaseService extends IService<LogBaseEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

