package com.huterox.whitehole.whiteholequiz.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huterox.common.utils.PageUtils;
import com.huterox.whiteholecould.entity.quiz.AnsComComentLikeEntity;

import java.util.Map;

/**
 * 这个表就是单纯的记录一下谁给这个在回答的下面的评论当中的评论点赞了（准确的说是评论的回复）
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
public interface AnsComComentLikeService extends IService<AnsComComentLikeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

