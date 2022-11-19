package com.huterox.whitehole.whiteholeblog.entity.surface.blogInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryStatusWithBlog {

    @NotEmpty
    private String userid;
    @NotNull
    private Long blogid;
}
