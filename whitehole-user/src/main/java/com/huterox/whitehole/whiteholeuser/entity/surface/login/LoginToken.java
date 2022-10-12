package com.huterox.whitehole.whiteholeuser.entity.surface.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginToken {
    //这个是我们的存储Redis里面的Token
    private String PcLoginToken;
    private String MobileLoginToken;
    private String LoginIP;
}
