package com.huterox.whiteholecould.entity.blog.Q;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommunityBlogUpQ {

    private String userid;
    private String blogTitle;
    private String context;
    private String info;
    private Integer level=1;
    private Integer blogtype=1;
    private Long communityid;
    /**
     * 这两个是我们额外需要的玩意
     * */
    private String communityUserid;
    private String communityName;
}
