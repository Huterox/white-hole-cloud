package com.huterox.whitehole.whiteholeblog.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huterox.common.utils.PageUtils;
import com.huterox.whiteholecould.entity.blog.BlogEntity;


import java.util.Map;

/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:21:46
 */
public interface BlogService extends IService<BlogEntity> {

    PageUtils queryPage(Map<String, Object> params) throws Exception;
}

