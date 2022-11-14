package com.huterox.whitehole.whiteholeuser.entity.surface.unity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnityMListEntity {
    @NotEmpty(message = "userid不能为空")
    private String userid;
    @NotNull(message = "page不能为空")
    private Integer page;
    @NotEmpty(message = "limit不能为空")
    private Integer limit;
    @NotNull(message = "communityid不能为空")
    private Long communityid;
}
