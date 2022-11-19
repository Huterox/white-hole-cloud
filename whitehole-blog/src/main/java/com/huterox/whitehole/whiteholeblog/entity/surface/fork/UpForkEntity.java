package com.huterox.whitehole.whiteholeblog.entity.surface.fork;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpForkEntity {

    @NotEmpty(message = "userid不能为空")
    private String userid;
    @NotNull(message = "blogid不能为空")
    private Long blogid;
    @NotEmpty(message = "blogUserid不能为空")
    private String blogUserid;
    @NotEmpty(message = "博客名称不能为空")
    private String blogTitle;
    @NotEmpty(message = "博文作者不能为空")
    private String blogUserNickname;
    private String blogImg;
}
