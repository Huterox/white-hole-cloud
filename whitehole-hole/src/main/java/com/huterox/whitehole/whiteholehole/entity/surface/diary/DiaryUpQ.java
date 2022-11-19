package com.huterox.whitehole.whiteholehole.entity.surface.diary;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiaryUpQ {

    @NotNull
    private String userid;
    @NotEmpty(message = "content")
    @Length(min = 10,max = 240,message="长度不能低于10个字符")
    private String content;
}
