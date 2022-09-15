package com.huterox.whitehole.whiteholeblog.dao;

import com.huterox.whitehole.whiteholeblog.entity.PullContentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 当用户同意之后，那么博文对应的内容就会被覆盖，同时在blog的贡献表当中显示提交用户的信息！
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:21:46
 */
@Mapper
public interface PullContentDao extends BaseMapper<PullContentEntity> {
	
}
