package com.huterox.whiteholecould.entity.message.Q;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoleAduitMsgQ {
    @NotEmpty
    private String userid;
    private String msg;
    private String msgtitle;
    private String linkid;
    private String linkid2;
    private Integer type;
}
