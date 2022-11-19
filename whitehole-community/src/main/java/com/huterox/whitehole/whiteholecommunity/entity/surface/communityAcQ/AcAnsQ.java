package com.huterox.whitehole.whiteholecommunity.entity.surface.communityAcQ;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcAnsQ {
    @NotNull
    private Long communityid;

    //这个userid是当前管理员的userid
    @NotNull
    private String userid;
    @NotNull
    private Long ansid;

}
