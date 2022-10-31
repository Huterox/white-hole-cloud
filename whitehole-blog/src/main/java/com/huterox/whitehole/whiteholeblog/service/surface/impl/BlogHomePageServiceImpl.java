package com.huterox.whitehole.whiteholeblog.service.surface.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;
import com.huterox.common.utils.SerializeUtil;
import com.huterox.whitehole.whiteholeblog.service.base.BlogService;
import com.huterox.whitehole.whiteholeblog.service.surface.BlogHomePageService;
import com.huterox.whiteholecould.entity.blog.BlogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class BlogHomePageServiceImpl implements BlogHomePageService {

    @Autowired
    BlogService blogService;

    @Override
    public R hotBlogList() throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        //组装请求博文列表所需要的数据，当访问的为内部接口时，所有的参数均为Map形式
        params.put("page","1");
        params.put("limit","10");
        params.put("accurate","many");
        //这里的key是没什么用的单纯占位置的
        params.put("key","1");
        QueryWrapper<BlogEntity> quizEntityQueryWrapper = new QueryWrapper<BlogEntity>();
        quizEntityQueryWrapper.eq("status",1)
                .eq("level",1)
                .orderByDesc("fork_number")
                .orderByDesc("collect_number")
                .orderByDesc("like_number")
                .orderByDesc("view_number");

        params.put("accurate_query", SerializeUtil.serialize(quizEntityQueryWrapper));
        PageUtils page = blogService.queryPage(params);
        return R.ok().put("page", page);
    }

    @Override
    public R lastBlogList() throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        //组装请求博文列表所需要的数据，当访问的为内部接口时，所有的参数均为Map形式
        params.put("page","1");
        params.put("limit","10");
        params.put("accurate","single");
        //此时指定HoleNULL，那么这个key不参与查询,但是需要进行占位，不能为空
        params.put("table_name","HoleNULL");
        params.put("key","1");
        params.put("order","desc");
        params.put("status","1");
        params.put("level","1");
        PageUtils page = blogService.queryPage(params);
        return R.ok().put("page", page);
    }
}
