package com.huterox.whitehole.whiteholeblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huterox.common.utils.PageUtils;
import com.huterox.whitehole.whiteholeblog.entity.ContributeEntity;

import java.util.Map;

/**
 * 博客的贡献者列表 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:21:46
 */
public interface ContributeService extends IService<ContributeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

