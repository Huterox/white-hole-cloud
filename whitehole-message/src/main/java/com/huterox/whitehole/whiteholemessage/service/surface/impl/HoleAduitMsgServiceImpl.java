package com.huterox.whitehole.whiteholemessage.service.surface.impl;

import com.huterox.common.utils.DateUtils;
import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholemessage.Enum.MessageActionEnum;
import com.huterox.whitehole.whiteholemessage.NettyServer.UserConnect.UserConnectPool;
import com.huterox.whitehole.whiteholemessage.entity.base.HoleAuditEntity;
import com.huterox.whitehole.whiteholemessage.service.base.HoleAuditService;
import com.huterox.whitehole.whiteholemessage.service.surface.HoleAduitMsgService;
import com.huterox.whitehole.whiteholemessage.utils.JsonUtils;
import com.huterox.whiteholecould.entity.message.Q.HoleAduitMsgQ;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class HoleAduitMsgServiceImpl implements HoleAduitMsgService {

    @Autowired
    HoleAuditService auditService;

    @Override
    public R holeaduitMsg(HoleAduitMsgQ holeAduitMsgQ) {
        //1.对消息进行存储,只要用户在线的话，我们就直接先给他签收一下
        String userid = holeAduitMsgQ.getUserid();
        Channel channel = UserConnectPool.getChannelFromMap(userid);
        HoleAuditEntity holeAuditEntity = new HoleAuditEntity();
        BeanUtils.copyProperties(holeAduitMsgQ,holeAuditEntity);
        holeAuditEntity.setCreateTime(DateUtils.getCurrentTime());

        if(channel!=null){
            //这边只是保证存在，双层保险，这个时候的话就是在线
            Channel realChannel = UserConnectPool.getChannelGroup().find(channel.id());
            if(realChannel!=null){
                holeAuditEntity.setStatus(1);
                //我们这边直接转发消息就好了，不需要再额外处理
                realChannel.writeAndFlush(
                        new TextWebSocketFrame(
                                JsonUtils.objectToJson(
                                        Objects.requireNonNull(R.ok().put("data", holeAuditEntity))
                                                .put("type", MessageActionEnum.HOLEADUITMSG.type)
                                )
                        )
                );
            }
        }

        //这里进行消息的存储
        auditService.save(holeAuditEntity);
        return R.ok();
    }

    @Override
    public R holeaduitMsgSingle() {
        /**
         * 我们做出如下约定：
         *  1.只要用户在线就给他签收
         *  2.我们客户端调用消息接受接口是在用户离线之后创建的
         *  3.客户端需要将本地的消息存储7天，客户端的读取状态是由客户端决定的
         *  4.服务端只保证，消息是否发送，签收（我们认为只要在线就可以签收）(1-已签收，2-未签收)
         * */

        return R.ok();
    }
}
