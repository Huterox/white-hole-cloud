package com.huterox.whitehole.whiteholeuser.service.base.impl;

import com.huterox.whiteholecould.entity.user.UserEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeuser.dao.UserDao;
import com.huterox.whitehole.whiteholeuser.service.base.UserService;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
//        在这里重写方法
        String key = (String) params.get("key");
        IPage<UserEntity> page_params = new Query<UserEntity>().getPage(params);
        QueryWrapper<UserEntity> userEntityQueryWrapper = new QueryWrapper<>();
        if(key!=null){
            userEntityQueryWrapper.like("nickname",key).or().
                    like("userid",key).or().
                    like("username",key)
            ;
        }
        IPage<UserEntity> page = this.page(
                page_params,
                userEntityQueryWrapper
        );

        return new PageUtils(page);
    }

}