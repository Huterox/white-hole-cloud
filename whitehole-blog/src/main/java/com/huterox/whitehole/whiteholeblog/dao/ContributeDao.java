package com.huterox.whitehole.whiteholeblog.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huterox.whiteholecould.entity.blog.ContributeEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 博客的贡献者列表 
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:21:46
 */
@Mapper
public interface ContributeDao extends BaseMapper<ContributeEntity> {
	
}
