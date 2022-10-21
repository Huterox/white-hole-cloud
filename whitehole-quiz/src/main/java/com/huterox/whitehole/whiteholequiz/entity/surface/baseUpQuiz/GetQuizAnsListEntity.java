package com.huterox.whitehole.whiteholequiz.entity.surface.baseUpQuiz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetQuizAnsListEntity {

    @NotNull
    private Long quizid;
    @NotNull(message = "page不能为空")
    private Integer page;
    @NotNull(message = "limit不能为空")
    private Integer limit;

}
