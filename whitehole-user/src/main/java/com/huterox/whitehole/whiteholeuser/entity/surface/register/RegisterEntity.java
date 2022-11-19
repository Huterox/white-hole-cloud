package com.huterox.whitehole.whiteholeuser.entity.surface.register;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterEntity {

//    @Pattern(regexp = "^[1]([3-9])[0-9]{9}$",message = "手机号格式不正确")
    private String phone;
    @NotEmpty(message = "用户昵称不能为空")
    private String nickname;
    @NotEmpty(message = "用户账号不能为空")
    private String username;
    @NotEmpty(message = "用户密码不能为空")
    @Length(min = 6,max = 18,message="密码必须是6-18位")
    private String password;
    @NotEmpty(message = "用户邮箱不能为空")
    @Email(message = "邮箱格式错位")
    private String email;
    @NotEmpty(message = "邮箱验证码不能为空")
    private String emailCode;
}
