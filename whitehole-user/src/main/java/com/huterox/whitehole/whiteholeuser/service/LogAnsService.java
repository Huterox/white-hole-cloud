package com.huterox.whitehole.whiteholeuser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huterox.common.utils.PageUtils;
import com.huterox.whitehole.whiteholeuser.entity.LogAnsEntity;

import java.util.Map;

/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
public interface LogAnsService extends IService<LogAnsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

