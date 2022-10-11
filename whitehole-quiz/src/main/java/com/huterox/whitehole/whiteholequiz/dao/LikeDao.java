package com.huterox.whitehole.whiteholequiz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huterox.whiteholecould.entity.quiz.LikeEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 收藏一定是喜欢，喜欢一定收藏，所以只要用户点击了收藏，就默认喜欢，我们对问题的受欢迎度进行筛选就是先通过收藏数，然后是喜欢数，最后是浏览量来搞的。


 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
@Mapper
public interface LikeDao extends BaseMapper<LikeEntity> {
	
}
