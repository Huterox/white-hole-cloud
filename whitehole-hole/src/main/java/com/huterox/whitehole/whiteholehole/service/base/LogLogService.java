package com.huterox.whitehole.whiteholehole.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huterox.common.utils.PageUtils;
import com.huterox.whiteholecould.entity.hole.LogLogEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 目前的话，我们的树洞只有两种功能，所以的话，我们这边就按照功能进行建表。
现在这个表是关于用户日志的表。
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:37:20
 */
public interface LogLogService extends IService<LogLogEntity> {

    PageUtils queryPage(Map<String, Object> params) throws Exception;
}

