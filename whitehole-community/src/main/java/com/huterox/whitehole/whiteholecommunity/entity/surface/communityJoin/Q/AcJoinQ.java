package com.huterox.whitehole.whiteholecommunity.entity.surface.communityJoin.Q;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcJoinQ {
    @NotNull
    private Long communityid;

    //这个userid是当前管理员的userid
    @NotNull
    private String userid;
    //我们社区成员的一个userid，当你同意之后，
    // 这个人就是我们的社区成员
    @NotNull
    private String membersid;
}
