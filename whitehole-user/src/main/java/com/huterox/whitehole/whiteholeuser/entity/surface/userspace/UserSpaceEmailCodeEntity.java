package com.huterox.whitehole.whiteholeuser.entity.surface.userspace;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSpaceEmailCodeEntity {
    @NotEmpty(message = "userid不能为空")
    private String userid;
    @NotEmpty(message = "password不能为空")
    private String password;
}
