package com.huterox.whitehole.whiteholecommunity.dao;

import com.huterox.whitehole.whiteholecommunity.entity.UsersEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 加入到社区的用户
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:34:12
 */
@Mapper
public interface UsersDao extends BaseMapper<UsersEntity> {
	
}
