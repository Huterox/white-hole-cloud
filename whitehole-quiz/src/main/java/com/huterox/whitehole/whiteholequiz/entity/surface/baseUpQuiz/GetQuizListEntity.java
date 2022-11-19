package com.huterox.whitehole.whiteholequiz.entity.surface.baseUpQuiz;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetQuizListEntity {

    private String userid;
    @NotNull(message = "page不能为空")
    private Integer page;
    @NotNull(message = "limit不能为空")
    private Integer limit;

}
