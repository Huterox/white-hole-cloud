package com.huterox.whitehole.whiteholequiz.dao;

import com.huterox.whitehole.whiteholequiz.entity.AnsComComentLikeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 这个表就是单纯的记录一下谁给这个在回答的下面的评论当中的评论点赞了（准确的说是评论的回复）
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
@Mapper
public interface AnsComComentLikeDao extends BaseMapper<AnsComComentLikeEntity> {
	
}