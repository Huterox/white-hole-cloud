package com.huterox.whitehole.whiteholequiz.dao;

import com.huterox.whitehole.whiteholequiz.entity.AnsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 问题的回答
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
@Mapper
public interface AnsDao extends BaseMapper<AnsEntity> {
	
}
