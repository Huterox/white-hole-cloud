package com.huterox.whitehole.whiteholeuser.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huterox.whiteholecould.entity.user.CollectArticleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 这个表是用来存储用户的收藏的博文的。这边是多了一个该文章的作者，减少了博文的修改时间。
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@Mapper
public interface CollectArticleDao extends BaseMapper<CollectArticleEntity> {
	
}
