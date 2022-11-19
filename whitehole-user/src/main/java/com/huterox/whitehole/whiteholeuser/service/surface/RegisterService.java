package com.huterox.whitehole.whiteholeuser.service.surface;

import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholeuser.entity.surface.register.GetEmailCodeEntity;
import com.huterox.whitehole.whiteholeuser.entity.surface.register.RegisterEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface RegisterService {

    public R register(RegisterEntity entity);
    public R emailCode(GetEmailCodeEntity entity);

}
