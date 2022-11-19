package com.huterox.whiteholecould.feign.message;

import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.message.Q.HoleAduitMsgQ;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("message")
@RequestMapping("/message/holeAduit")
public interface FeignHoleAduitMsgService {

    @PostMapping("/aduit")
    public R holeAduitMsg(@RequestBody HoleAduitMsgQ holeAduitMsgQ);
}
