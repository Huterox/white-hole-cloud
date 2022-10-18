package com.huterox.whitehole.whiteholeuser.entity.surface.userspace;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSpaceEmailCodeSaveEntity {
    private String emailCode;
    private String password;
}
