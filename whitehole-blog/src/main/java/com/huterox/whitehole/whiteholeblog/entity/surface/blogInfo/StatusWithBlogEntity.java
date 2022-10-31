package com.huterox.whitehole.whiteholeblog.entity.surface.blogInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusWithBlogEntity {

    private boolean isCollect = false;
    private boolean isLike = false;
    private boolean isfork = false;
}
