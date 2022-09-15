package com.huterox.whitehole.whiteholeblog.dao;

import com.huterox.whitehole.whiteholeblog.entity.ForkEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章被fork的状况
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:21:46
 */
@Mapper
public interface ForkDao extends BaseMapper<ForkEntity> {
	
}
