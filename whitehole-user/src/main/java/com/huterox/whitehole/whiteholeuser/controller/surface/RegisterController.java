package com.huterox.whitehole.whiteholeuser.controller.surface;

import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholeuser.entity.surface.register.GetEmailCodeEntity;
import com.huterox.whitehole.whiteholeuser.entity.surface.register.RegisterEntity;
import com.huterox.whitehole.whiteholeuser.service.surface.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @PostMapping("/user/register")
    public R register(@Validated @RequestBody RegisterEntity entity){
        return registerService.register(entity);
    }

    @PostMapping ("/user/emailcode")
    public R emailCode(@Validated @RequestBody GetEmailCodeEntity entity
    )
    {
        return registerService.emailCode(entity);
    }

}
