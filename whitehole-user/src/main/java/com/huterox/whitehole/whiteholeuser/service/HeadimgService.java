package com.huterox.whitehole.whiteholeuser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huterox.common.utils.PageUtils;
import com.huterox.whitehole.whiteholeuser.entity.HeadimgEntity;

import java.util.Map;

/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
public interface HeadimgService extends IService<HeadimgEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

