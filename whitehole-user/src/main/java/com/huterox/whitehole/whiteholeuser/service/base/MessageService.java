package com.huterox.whitehole.whiteholeuser.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huterox.common.utils.PageUtils;
import com.huterox.whiteholecould.entity.user.MessageEntity;

import java.util.Map;

/**
 * 这个表是用来记录用户消息的,关于消息的具体的一些内容，或者是详情页面
是由负责该消息，或者说产生这个消息的服务去处理，显示完整的消息。
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
public interface MessageService extends IService<MessageEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

