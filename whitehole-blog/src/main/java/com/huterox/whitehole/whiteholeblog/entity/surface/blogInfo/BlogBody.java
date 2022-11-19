package com.huterox.whitehole.whiteholeblog.entity.surface.blogInfo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogBody implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 这边的话userid啥的都是指文章的作者
     * */
    private String userid;
    private Long blogid;
    private String content;
    private String blogTitle;
    private String userNickname;
    private String userImg;
    private String createTime;
    private Integer viewNumber;
    private Integer likeNumber;
    private Integer collectNumber;
    private Integer forkNumber;
    private String blogtype;
    private String blogimg;

}
