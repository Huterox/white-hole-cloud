package com.huterox.whitehole.whiteholequiz.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huterox.common.utils.PageUtils;
import com.huterox.whiteholecould.entity.quiz.AnsCommentEntity;

import java.util.Map;

/**
 * 问题回答的评论
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
public interface AnsCommentService extends IService<AnsCommentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

