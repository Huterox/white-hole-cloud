package com.huterox.whitehole.whiteholeuser.dao;

import com.huterox.whitehole.whiteholeuser.entity.base.AchievementEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户的一些信息的记录，例如用户的签到天数，用户的称号，称号分为，技术类型，活跃类型，社交类型

每个类型设置4个，但是现在由于还在测试构建阶段，所以的话，我们就只设置一个活跃类型的，并且只有一个，也就是我们的签到天数。



 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@Mapper
public interface AchievementDao extends BaseMapper<AchievementEntity> {
	
}
