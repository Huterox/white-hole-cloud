package com.huterox.whitehole.whiteholeuser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huterox.common.utils.PageUtils;
import com.huterox.whitehole.whiteholeuser.entity.AnsEntity;

import java.util.Map;

/**
 * 这个是用户的回答。



 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
public interface AnsService extends IService<AnsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

