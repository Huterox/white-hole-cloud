package com.huterox.whitehole.whiteholeuser.entity.surface.userspace;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserHandImgEntity {
    @NotEmpty(message = "userid不能为空")
    private String userid;
    @NotEmpty(message = "imgpath不能为空")
    private String imgpath;
}
