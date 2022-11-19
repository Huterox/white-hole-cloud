package com.huterox.whitehole.whiteholeuser.entity.surface.userspace;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoEditEntity {

    @NotEmpty(message = "用户密码不能为空")
    private String userid;
    private String nickname;
    @Length(min = 0,max = 100,message="长度不能超过100")
    private String home;
    private String email;
    @Length(min = 0,max = 100,message="长度不能超过100")
    private String info;
}
