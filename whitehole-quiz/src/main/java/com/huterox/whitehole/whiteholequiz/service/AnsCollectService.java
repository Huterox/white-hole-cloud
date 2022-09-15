package com.huterox.whitehole.whiteholequiz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huterox.common.utils.PageUtils;
import com.huterox.whitehole.whiteholequiz.entity.AnsCollectEntity;

import java.util.Map;

/**
 * 这个是问题回答的收藏，有哪些用户收藏了，我们的筛选标准和我们这个的问题的标准是一样的
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
public interface AnsCollectService extends IService<AnsCollectEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

