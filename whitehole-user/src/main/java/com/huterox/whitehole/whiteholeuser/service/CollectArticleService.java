package com.huterox.whitehole.whiteholeuser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huterox.common.utils.PageUtils;
import com.huterox.whitehole.whiteholeuser.entity.CollectArticleEntity;

import java.util.Map;

/**
 * 这个表是用来存储用户的收藏的博文的。这边是多了一个该文章的作者，减少了博文的修改时间。
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
public interface CollectArticleService extends IService<CollectArticleEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

