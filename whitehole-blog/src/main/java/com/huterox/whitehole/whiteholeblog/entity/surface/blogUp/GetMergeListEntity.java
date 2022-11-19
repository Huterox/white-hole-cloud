package com.huterox.whitehole.whiteholeblog.entity.surface.blogUp;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetMergeListEntity {

    private String userid;
    @NotNull(message = "page不能为空")
    private Integer page;
    @NotNull(message = "limit不能为空")
    private Integer limit;

}
