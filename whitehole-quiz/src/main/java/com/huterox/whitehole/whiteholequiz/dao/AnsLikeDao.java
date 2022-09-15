package com.huterox.whitehole.whiteholequiz.dao;

import com.huterox.whitehole.whiteholequiz.entity.AnsLikeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 这个是回答的一个喜欢的记录
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
@Mapper
public interface AnsLikeDao extends BaseMapper<AnsLikeEntity> {
	
}
