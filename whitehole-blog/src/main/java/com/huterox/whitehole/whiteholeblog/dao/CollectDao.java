package com.huterox.whitehole.whiteholeblog.dao;

import com.huterox.whitehole.whiteholeblog.entity.CollectEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 这个是问题的收藏，我们来看看是谁收藏了这个问题
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:21:46
 */
@Mapper
public interface CollectDao extends BaseMapper<CollectEntity> {
	
}
