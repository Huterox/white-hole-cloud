package com.huterox.whitehole.whiteholehole.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huterox.whiteholecould.entity.hole.LogLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 目前的话，我们的树洞只有两种功能，所以的话，我们这边就按照功能进行建表。
现在这个表是关于用户日志的表。
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:37:20
 */
@Mapper
public interface LogLogDao extends BaseMapper<LogLogEntity> {
	
}
