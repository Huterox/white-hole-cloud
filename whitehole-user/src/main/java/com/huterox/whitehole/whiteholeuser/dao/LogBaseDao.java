package com.huterox.whitehole.whiteholeuser.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huterox.whiteholecould.entity.user.LogBaseEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户的一些日志信息
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@Mapper
public interface LogBaseDao extends BaseMapper<LogBaseEntity> {
	
}
