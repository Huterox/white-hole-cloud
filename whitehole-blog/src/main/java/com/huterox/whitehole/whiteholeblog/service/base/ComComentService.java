package com.huterox.whitehole.whiteholeblog.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huterox.common.utils.PageUtils;
import com.huterox.whiteholecould.entity.blog.ComComentEntity;


import java.util.Map;

/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:21:46
 */
public interface ComComentService extends IService<ComComentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

