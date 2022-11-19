package com.huterox.whitehole.whiteholequiz.entity.surface.baseUpQuiz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpQuizEntity {
    @NotEmpty(message = "userid不能为空")
    private String userid;
    @NotEmpty(message = "userid不能为空")
    private String quizTitle;
    @Length(min = 10,max = 500,message="长度不能超过500")
    private String quizContent;
    @NotNull
    private Long community;
}
