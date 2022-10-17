package com.huterox.whitehole.whiteholeuser.service.surface.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholeuser.entity.surface.userspace.UserSpaceInfoListQueryEntity;
import com.huterox.whitehole.whiteholeuser.exception.BizCodeEnum;
import com.huterox.whitehole.whiteholeuser.service.base.BlogForkService;
import com.huterox.whitehole.whiteholeuser.service.surface.UserSpaceService;
import com.huterox.whiteholecould.entity.blog.BlogEntity;
import com.huterox.whiteholecould.feign.blog.FeignBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserSpaceServiceImpl implements UserSpaceService {

    @Autowired
    FeignBlogService feignBlogService;

    @Autowired
    BlogForkService blogForkService;

    @Override
    public R userIsLogin(String userid) {
        return R.ok(BizCodeEnum.SUCCESSFUL.getMsg());
    }

    @Override
    public R userAllArticle(UserSpaceInfoListQueryEntity entity) {
        HashMap<String, Object> params = new HashMap<>();
        //组装请求博文列表所需要的数据，当访问的为内部接口时，所有的参数均为Map形式
        params.put("page",entity.getPage());
        params.put("limit",entity.getLimit());
        params.put("accurate","many");
        //其实这个key在咱们这里压根没啥用，但是必须要这个玩意，否则不会触发下面的逻辑
        //被写死了，不想改了。
        params.put("key",entity.getUserid());
        QueryWrapper<BlogEntity> blogEntityQueryWrapper = new QueryWrapper<BlogEntity>();
        blogEntityQueryWrapper.eq("userid",entity.getUserid())
                        .orderByDesc("blogid");

        params.put("accurate_query",blogEntityQueryWrapper);
        //开始请求访问
        return feignBlogService.list(params);
    }

    @Override
    public R userStatusArticle(UserSpaceInfoListQueryEntity entity) {
        HashMap<String, Object> params = new HashMap<>();
        //组装请求博文列表所需要的数据，当访问的为内部接口时，所有的参数均为Map形式
        params.put("page",entity.getPage());
        params.put("limit",entity.getLimit());
        params.put("accurate","many");
        params.put("key",entity.getUserid());
        QueryWrapper<BlogEntity> blogEntityQueryWrapper = new QueryWrapper<BlogEntity>();
        blogEntityQueryWrapper.eq("userid",entity.getUserid())
                .ne("status",1)
                .orderByDesc("blogid");

        params.put("accurate_query",blogEntityQueryWrapper);
        //开始请求访问
        return feignBlogService.list(params);
    }

    @Override
    public R userPrivateArticle(UserSpaceInfoListQueryEntity entity) {
        HashMap<String, Object> params = new HashMap<>();
        //组装请求博文列表所需要的数据，当访问的为内部接口时，所有的参数均为Map形式
        params.put("page",entity.getPage());
        params.put("limit",entity.getLimit());
        params.put("accurate","single");
        params.put("table_name","userid");
        params.put("order","desc");
        params.put("status",1);
        params.put("level",2);
        params.put("key",entity.getUserid());
        //开始请求访问
        return feignBlogService.list(params);
    }

    @Override
    public R userForkArticle(UserSpaceInfoListQueryEntity entity) {
//        这里调用的是咱自己的本地接口
        HashMap<String, Object> params = new HashMap<>();
        params.put("page",entity.getPage().toString());
        params.put("limit",entity.getLimit().toString());
        params.put("key",entity.getUserid());
        params.put("accurate","single");
        params.put("order","desc");
        params.put("table_name","userid");
        PageUtils page = blogForkService.queryPage(params);
        return R.ok().put("page", page);
    }

}
