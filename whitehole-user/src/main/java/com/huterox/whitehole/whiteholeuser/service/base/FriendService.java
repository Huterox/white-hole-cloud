package com.huterox.whitehole.whiteholeuser.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huterox.common.utils.PageUtils;
import com.huterox.whiteholecould.entity.user.FriendEntity;

import java.util.Map;

/**
 * 这个是用户的好友列表，由于服务器的成本问题，我们的聊天和消息通知服务都是被动式的。
这个表式双向的，某一方删除，双方的信息都会删除。为了安全这边也是需要校验码的。
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:08
 */
public interface FriendService extends IService<FriendEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

