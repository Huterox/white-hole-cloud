package com.huterox.whitehole.whiteholequiz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huterox.whiteholecould.entity.quiz.CollectEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 这个是问题的收藏，我们来看看是谁收藏了这个问题
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
@Mapper
public interface CollectDao extends BaseMapper<CollectEntity> {
	
}
