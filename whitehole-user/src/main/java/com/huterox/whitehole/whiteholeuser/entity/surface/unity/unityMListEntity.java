package com.huterox.whitehole.whiteholeuser.entity.surface.unity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class unityMListEntity {
    @NotEmpty(message = "userid不能为空")
    private String userid;
    @NotEmpty(message = "page不能为空")
    private Integer page;
    @NotEmpty(message = "limit不能为空")
    private Integer limit;
}
