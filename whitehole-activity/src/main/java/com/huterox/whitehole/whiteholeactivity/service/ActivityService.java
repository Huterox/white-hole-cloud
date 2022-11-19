package com.huterox.whitehole.whiteholeactivity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huterox.common.utils.PageUtils;
import com.huterox.whitehole.whiteholeactivity.entity.ActivityEntity;

import java.util.Map;

/**
 * 这个是我们创建的活动的一些内容，这个是在运营阶段由管理人员创建的，当运营确定好活动之后，
我们这边就会进行创建新的活动，之后在技术层面实现，注意：activity 和 admin 都是管理人使用的模块。

注意的是，我们的这个记录的是我们这个由管理人员创建的玩意来的，实际上我们还有系统自动的一些活动，这些活动是系统级别的。




 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:34:46
 */
public interface ActivityService extends IService<ActivityEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

