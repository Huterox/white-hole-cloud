package com.huterox.whitehole.whiteholecommunity.entity.surface.communityJoin.Q;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinQ {
    @NotNull
    private Long communityid;
    @NotNull
    private String userid;

}
