package com.huterox.whitehole.whiteholeblog.entity.surface.blogUp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpBlogEntity {

    @NotEmpty(message = "userid不能为空")
    private String userid;
    @NotEmpty(message = "quizTitle不能为空")
    @Length(min = 10,message="长度不能低于10个字符")
    private String blogTitle;
    //文章的Markdown文档
    @NotEmpty(message = "context不能为空")
    @Length(min = 100,message="长度不能低于10个字符")
    private String context;
    @Length(min = 4,max = 100,message="长度不能低于10个字符")
    private String info;
    @NotNull
    private Integer level=1;
    @NotNull
    private Integer blogtype=1;

    private Long community;
}
