package com.huterox.whitehole.whiteholeuser.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huterox.common.utils.PageUtils;
import com.huterox.whiteholecould.entity.user.ManageCommunityEntity;

import java.util.Map;

/**
 * 这个是用户创建的社区
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
public interface ManageCommunityService extends IService<ManageCommunityEntity> {

    PageUtils queryPage(Map<String, Object> params) throws Exception;
}

