package com.huterox.whitehole.whiteholeuser.entity.surface.userspace;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserShowInfoEntity {
    private String nickname;
    private String phone;
    private String email;
    private String info;
    private Integer holeValue;
    private String home;
    private String userpic;
    private String ipAddr;
}
