package com.huterox.whitehole.whiteholequiz.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huterox.common.utils.PageUtils;
import com.huterox.whiteholecould.entity.quiz.AnsLikeEntity;

import java.util.Map;

/**
 * 这个是回答的一个喜欢的记录
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
public interface AnsLikeService extends IService<AnsLikeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

