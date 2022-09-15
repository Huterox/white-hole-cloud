package com.huterox.whitehole.whiteholeuser.dao;

import com.huterox.whitehole.whiteholeuser.entity.QuizEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 这个表是中间表，不存在主键。
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@Mapper
public interface QuizDao extends BaseMapper<QuizEntity> {
	
}
