package com.huterox.whitehole.whiteholeuser.entity.surface.login;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginEntity {

    private String username;
    @NotEmpty(message = "用户密码不能为空")
    @Length(min = 6,max = 18,message="密码必须是6-18位")
    private String password;
//    PC,mobile
    @NotEmpty(message = "设备类型不能为空")
    private String type;

}
