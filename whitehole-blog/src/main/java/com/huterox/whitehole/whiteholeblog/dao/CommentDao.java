package com.huterox.whitehole.whiteholeblog.dao;

import com.huterox.whitehole.whiteholeblog.entity.CommentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 问题回答的评论
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:21:46
 */
@Mapper
public interface CommentDao extends BaseMapper<CommentEntity> {
	
}
