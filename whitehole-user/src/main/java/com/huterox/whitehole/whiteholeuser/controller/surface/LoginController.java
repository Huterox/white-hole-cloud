package com.huterox.whitehole.whiteholeuser.controller.surface;

import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholeuser.entity.surface.login.LoginEntity;
import com.huterox.whitehole.whiteholeuser.service.surface.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;
    @PostMapping("/user/login")
    public R login (@Validated @RequestBody LoginEntity entity){
        return loginService.Login(entity);
    }
}
