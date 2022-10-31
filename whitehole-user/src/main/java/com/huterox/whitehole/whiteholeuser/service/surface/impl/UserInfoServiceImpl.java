package com.huterox.whitehole.whiteholeuser.service.surface.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholeuser.entity.surface.userinfo.UserInfoEntity;
import com.huterox.whitehole.whiteholeuser.entity.surface.userinfo.UserInfoListQueryEntity;
import com.huterox.whitehole.whiteholeuser.exception.BizCodeEnum;
import com.huterox.whitehole.whiteholeuser.service.base.HeadimgService;
import com.huterox.whitehole.whiteholeuser.service.base.UserService;
import com.huterox.whitehole.whiteholeuser.service.surface.UserInfoService;
import com.huterox.whiteholecould.entity.user.HeadimgEntity;
import com.huterox.whiteholecould.entity.user.UserEntity;
import com.huterox.whiteholecould.feign.blog.FeignBlogService;
import com.huterox.whiteholecould.feign.community.FeignCommunityService;
import com.huterox.whiteholecould.feign.quiz.FeignAnsService;
import com.huterox.whiteholecould.feign.quiz.FeignQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 *这里面的所有方法是用来查询用户信息的，所有的求取格式为Get  */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserService userService;
    @Autowired
    HeadimgService headimgService;

    @Autowired
    FeignBlogService feignBlogService;

    @Autowired
    FeignQuizService feignQuizService;

    @Autowired
    FeignAnsService feignAnsService;

    @Autowired
    FeignCommunityService feignCommunityService;

    @Override
    public R userInfo(String userid) {
        UserEntity User = userService.getOne(
                new QueryWrapper<UserEntity>().eq("userid", userid)
        );
        if(User!=null){
            UserInfoEntity userInfoEntity = new UserInfoEntity();
            userInfoEntity.setUserid(User.getUserid());
            userInfoEntity.setUserinfo(User.getInfo());
            userInfoEntity.setNickname(User.getNickname());
            //查询用户的头像信息
            HeadimgEntity headimg = headimgService.getOne(
                    new QueryWrapper<HeadimgEntity>().eq("userid", userid)
                            .orderByDesc("imgid")
                            .last("limit 0,1")

            );
            if(headimg!=null){
                userInfoEntity.setUserpic(headimg.getImgpath());
            }else {
                userInfoEntity.setUserpic(null);
            }
//            可以不写BizCodeEnum.SUCCESSFUL.getMsg()，默认有填写
            return R.ok(BizCodeEnum.SUCCESSFUL.getMsg())
                    .put("User",userInfoEntity);

        }else {
            return R.error(BizCodeEnum.NO_SUCHUSER.getCode(),BizCodeEnum.NO_SUCHUSER.getMsg());
        }
    }

    @Override
    public R userInfoArticle(UserInfoListQueryEntity entity){
        HashMap<String, Object> params = new HashMap<>();
        //组装请求博文列表所需要的数据，当访问的为内部接口时，所有的参数均为Map形式
        params.put("page",entity.getPage());
        params.put("limit",entity.getLimit());
        params.put("accurate","single");
        params.put("table_name","userid");
        params.put("order","desc");
        params.put("status",1);
        params.put("level",1);
        params.put("key",entity.getUserid());
        //开始请求访问
        return feignBlogService.list(params);
    }

    @Override
    public R userInfoQuiz(UserInfoListQueryEntity entity) {
        HashMap<String, Object> params = new HashMap<>();
        //组装请求博文列表所需要的数据，当访问的为内部接口时，所有的参数均为Map形式
        params.put("page",entity.getPage());
        params.put("limit",entity.getLimit());
        params.put("accurate","single");
        params.put("table_name","userid");
        params.put("order","desc");
        params.put("status",1);
        params.put("key",entity.getUserid());
        return feignQuizService.list(params);
    }

    @Override
    public R userInfoAns(UserInfoListQueryEntity entity) {
        HashMap<String, Object> params = new HashMap<>();
        //组装请求博文列表所需要的数据，当访问的为内部接口时，所有的参数均为Map形式
        params.put("page",entity.getPage());
        params.put("limit",entity.getLimit());
        params.put("accurate","single");
        params.put("table_name","userid");
        params.put("order","desc");
        params.put("status",1);
        params.put("key",entity.getUserid());
        return feignAnsService.list(params);
    }

    @Override
    public R userInfoUnity(UserInfoListQueryEntity entity) {
        HashMap<String, Object> params = new HashMap<>();
        //组装请求博文列表所需要的数据，当访问的为内部接口时，所有的参数均为Map形式
        params.put("page",entity.getPage());
        params.put("limit",entity.getLimit());
        params.put("accurate","single");
        params.put("table_name","userid");
        params.put("order","desc");
        params.put("status",1);
        params.put("key",entity.getUserid());
        return feignCommunityService.list(params);
    }
}
