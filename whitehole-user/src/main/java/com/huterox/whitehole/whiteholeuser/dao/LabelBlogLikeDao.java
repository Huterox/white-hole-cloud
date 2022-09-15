package com.huterox.whitehole.whiteholeuser.dao;

import com.huterox.whitehole.whiteholeuser.entity.LabelBlogLikeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 这个是用户感兴趣的博文标签
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@Mapper
public interface LabelBlogLikeDao extends BaseMapper<LabelBlogLikeEntity> {
	
}
