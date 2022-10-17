package com.huterox.whitehole.whiteholemessage.Service;

import com.huterox.whitehole.whiteholemessage.Bean.DataContent;

public interface PushMsgService {

    /**
     * 推送给指定用户
     */
    void pushMsgToOne(DataContent dataContent);

    /**
     * 推送给所有用户
     */
    void pushMsgToAll(DataContent dataContent);

}