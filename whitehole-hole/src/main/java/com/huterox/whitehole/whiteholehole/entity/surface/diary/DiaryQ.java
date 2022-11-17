package com.huterox.whitehole.whiteholehole.entity.surface.diary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiaryQ {

    @NotNull
    private String userid;
    @NotNull(message = "page不能为空")
    private Integer page;
    @NotNull(message = "limit不能为空")
    private Integer limit;
}
