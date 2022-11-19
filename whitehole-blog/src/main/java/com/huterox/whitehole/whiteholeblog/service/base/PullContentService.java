package com.huterox.whitehole.whiteholeblog.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huterox.common.utils.PageUtils;
import com.huterox.whiteholecould.entity.blog.PullContentEntity;


import java.util.Map;

/**
 * 当用户同意之后，那么博文对应的内容就会被覆盖，同时在blog的贡献表当中显示提交用户的信息！
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:21:46
 */
public interface PullContentService extends IService<PullContentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

