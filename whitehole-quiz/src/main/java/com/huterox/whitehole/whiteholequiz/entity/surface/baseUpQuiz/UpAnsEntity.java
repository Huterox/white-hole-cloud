package com.huterox.whitehole.whiteholequiz.entity.surface.baseUpQuiz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpAnsEntity {

    //回答者的userid
    @NotEmpty(message = "userid不能为空")
    private String userid;
    @NotNull(message = "quizid不能为空")
    private Long quizid;
    @NotEmpty(message = "quizTitle不能为空")
    private String quizTitle;
    //回答的Markdown文档
    @NotEmpty(message = "context不能为空")
    @Length(min = 10,message="长度不能低于10个字符")
    private String context;
    //社区的id
    private Long communityid;

}
