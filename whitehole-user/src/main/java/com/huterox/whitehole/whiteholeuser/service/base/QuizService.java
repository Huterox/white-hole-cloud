package com.huterox.whitehole.whiteholeuser.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huterox.common.utils.PageUtils;
import com.huterox.whiteholecould.entity.user.QuizEntity;

import java.util.Map;

/**
 * 这个表是中间表，不存在主键。
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
public interface QuizService extends IService<QuizEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

