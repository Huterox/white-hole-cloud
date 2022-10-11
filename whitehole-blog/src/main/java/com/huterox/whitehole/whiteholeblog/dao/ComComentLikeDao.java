package com.huterox.whitehole.whiteholeblog.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huterox.whiteholecould.entity.blog.ComComentLikeEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 这个表就是单纯的记录一下谁给这个在回答的下面的评论当中的评论点赞了（准确的说是评论的回复）
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:21:46
 */
@Mapper
public interface ComComentLikeDao extends BaseMapper<ComComentLikeEntity> {
	
}
