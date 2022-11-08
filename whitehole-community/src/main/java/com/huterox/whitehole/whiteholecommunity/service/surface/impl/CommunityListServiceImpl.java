package com.huterox.whitehole.whiteholecommunity.service.surface.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;
import com.huterox.common.utils.SerializeUtil;
import com.huterox.whitehole.whiteholecommunity.entity.surface.communityList.Q.GetunityListQ;
import com.huterox.whitehole.whiteholecommunity.service.base.BlogsService;
import com.huterox.whitehole.whiteholecommunity.service.base.CommunityService;
import com.huterox.whitehole.whiteholecommunity.service.surface.CommunityListService;
import com.huterox.whiteholecould.entity.blog.BlogEntity;
import com.huterox.whiteholecould.entity.community.CommunityEntity;
import com.huterox.whiteholecould.entity.quiz.QuizEntity;
import com.huterox.whiteholecould.feign.blog.FeignBlogService;
import com.huterox.whiteholecould.feign.quiz.FeignQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CommunityListServiceImpl implements CommunityListService{
    @Autowired
    CommunityService communityService;

    @Autowired
    BlogsService blogsService;

    @Autowired
    FeignBlogService feignBlogService;

    @Autowired
    FeignQuizService feignQuizService;


    @Override
    public R unityList(GetunityListQ entity) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        //组装请求博文列表所需要的数据，当访问的为内部接口时，所有的参数均为Map形式
        params.put("page",String.valueOf(entity.getPage()));
        params.put("limit",String.valueOf(entity.getLimit()));
        params.put("accurate","many");
        //这里的key是没什么用的单纯占位置的
        params.put("key","1");
        QueryWrapper<CommunityEntity> communityEntityQueryWrapper = new QueryWrapper<>();
        communityEntityQueryWrapper.eq("status",1)
                .orderByDesc("blog_number")
                .orderByDesc("member_number")
                .orderByDesc("community_like");

        params.put("accurate_query", SerializeUtil.serialize(communityEntityQueryWrapper));
        PageUtils page = communityService.queryPage(params);
        return  R.ok().put("page", page);
    }

    @Override
    public R unityBlogList(GetunityListQ entity) throws Exception {
        /**
         * 这里主要是完成咱们的这个社区的首页的博文的展示
         * 咱们这个也是按照流量来进行排序滴。
         * */
        HashMap<String, Object> params = new HashMap<>();
        params.put("page",String.valueOf(entity.getPage()));
        params.put("limit",String.valueOf(entity.getLimit()));
        params.put("accurate","many");
        //这里的key是没什么用的单纯占位置的
        params.put("key","1");
        QueryWrapper<BlogEntity> blogEntityQueryWrapper = new QueryWrapper<BlogEntity>();
        blogEntityQueryWrapper.eq("status",1)
                .eq("level",1)
                .eq("communityid",entity.getCommunityid())
                .orderByDesc("fork_number")
                .orderByDesc("collect_number")
                .orderByDesc("like_number")
                .orderByDesc("view_number");

        params.put("accurate_query", SerializeUtil.serialize(blogEntityQueryWrapper));
        return feignBlogService.list(params);

    }

    @Override
    public R unityQuiList(GetunityListQ entity) throws Exception {
        /**
         * 这个是社区的问答的一个展示
         * */

        HashMap<String, Object> params = new HashMap<>();
        params.put("page",entity.getPage());
        params.put("limit",entity.getLimit());
        params.put("accurate","many");
        //这里的key是没什么用的单纯占位置的
        params.put("key","1");
        QueryWrapper<QuizEntity> quizEntityQueryWrapper = new QueryWrapper<QuizEntity>();
        quizEntityQueryWrapper.eq("status",1)
                .eq("communityid",entity.getCommunityid())
                .orderByDesc("quiz_collect_number")
                .orderByDesc("quiz_like_number")
                .orderByDesc("quiz_view_number");

        params.put("accurate_query", SerializeUtil.serialize(quizEntityQueryWrapper));
        return feignQuizService.list(params);
    }
}
