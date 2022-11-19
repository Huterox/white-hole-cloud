package com.huterox.whitehole.whiteholequiz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huterox.whiteholecould.entity.quiz.AnsCommentEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 问题回答的评论
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
@Mapper
public interface AnsCommentDao extends BaseMapper<AnsCommentEntity> {
	
}
