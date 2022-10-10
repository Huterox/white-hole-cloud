package com.huterox.whitehole.whiteholeuser.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huterox.common.utils.PageUtils;
import com.huterox.whitehole.whiteholeuser.entity.base.LabelBlogLikeEntity;

import java.util.Map;

/**
 * 这个是用户感兴趣的博文标签
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
public interface LabelBlogLikeService extends IService<LabelBlogLikeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

