package com.huterox.whitehole.whiteholequiz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huterox.common.utils.PageUtils;
import com.huterox.whitehole.whiteholequiz.entity.AnsComComentEntity;

import java.util.Map;

/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
public interface AnsComComentService extends IService<AnsComComentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
