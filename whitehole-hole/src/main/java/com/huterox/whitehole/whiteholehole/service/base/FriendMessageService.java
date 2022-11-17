package com.huterox.whitehole.whiteholehole.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huterox.common.utils.PageUtils;
import com.huterox.whiteholecould.entity.hole.FriendMessageEntity;

import java.util.Map;

/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:37:20
 */
public interface FriendMessageService extends IService<FriendMessageEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

