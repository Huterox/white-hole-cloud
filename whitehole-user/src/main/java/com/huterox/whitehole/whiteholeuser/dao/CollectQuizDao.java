package com.huterox.whitehole.whiteholeuser.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huterox.whiteholecould.entity.user.CollectQuizEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 问题回答交流的本质还是博文的逻辑，但是区别是，问答模块具备强交流性。

用户可能收藏的是整个问题，包括问题当中的所有回答，也有可能是针对某一个问题的，其他用户的回答，因此这边还需要创建一个新的表。
专门用于存储用户收藏的问题。
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@Mapper
public interface CollectQuizDao extends BaseMapper<CollectQuizEntity> {
	
}
