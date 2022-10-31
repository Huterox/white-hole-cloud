package com.huterox.whitehole.whiteholeblog.service.surface.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huterox.common.utils.DateUtils;
import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholeblog.entity.surface.fork.UpForkEntity;
import com.huterox.whitehole.whiteholeblog.exception.BizCodeEnum;
import com.huterox.whitehole.whiteholeblog.service.base.ForkService;
import com.huterox.whitehole.whiteholeblog.service.surface.BlogForkService;
import com.huterox.whitehole.whiteholeblog.utils.RedisTransKey;
import com.huterox.whitehole.whiteholeblog.utils.RedisUtils;
import com.huterox.whiteholecould.entity.blog.ForkEntity;
import com.huterox.whiteholecould.entity.user.BlogForkEntity;
import com.huterox.whiteholecould.feign.user.FeignBlogForkService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class BlogForkServiceImpl implements BlogForkService {

    @Autowired
    ForkService forkService;

    @Autowired
    FeignBlogForkService feignBlogForkService;

    @Autowired
    RedisUtils redisUtils;

    @Override
    public R forkBlog(UpForkEntity entity) {
        /**
         * 博文的fork
         * 1. 跟新博文自己的fork情况
         * 2. 更新用户对博文的fork情况
         * */
        if(redisUtils.hasKey(RedisTransKey.getForkBlogKey(entity.getUserid()))){
            return R.error(BizCodeEnum.CAN_NOT_FORK.getCode(), BizCodeEnum.CAN_NOT_FORK.getMsg());
        }
        String backMessage = "fork成功";

        String blogUserid = entity.getBlogUserid();
        String userid = entity.getUserid();
        if(blogUserid.equals(userid)){
            return R.error(BizCodeEnum.THE_SOMEFORK.getCode(),BizCodeEnum.THE_SOMEFORK.getMsg());
        }
        //查看是不是已经fork了，如果是的话，那么给用户取消
        QueryWrapper<ForkEntity> hasquery = new QueryWrapper<>();
        hasquery.eq("userid", entity.getUserid())
                .eq("blogid", entity.getBlogid());
        ForkEntity one = forkService.getOne(hasquery);
        if(one!=null){
            backMessage = "已取消fork";
            forkService.remove(hasquery);
            Map<String, Object> params = new HashMap<>();
            params.put("userid",entity.getUserid());
            params.put("articleid",entity.getBlogid());
            feignBlogForkService.deletefork(params);
            return R.ok(backMessage);
        }
        ForkEntity forkEntity = new ForkEntity();
        BeanUtils.copyProperties(entity,forkEntity);
        forkEntity.setCreateTime(DateUtils.getCurrentTime());
        forkService.save(forkEntity);

        //跟新用户的一个fork情况
        BlogForkEntity blogForkEntity = new BlogForkEntity();
        blogForkEntity.setArticleid(forkEntity.getBlogid());
        blogForkEntity.setArticleImg(entity.getBlogImg());
        blogForkEntity.setUserid(entity.getUserid());
        blogForkEntity.setCreatTime(forkEntity.getCreateTime());
        blogForkEntity.setArticleUserNickname(entity.getBlogUserNickname());
        blogForkEntity.setArticleUserid(entity.getBlogUserid());
        blogForkEntity.setArticleTitle(entity.getBlogTitle());
        feignBlogForkService.save(blogForkEntity);

        redisUtils.set(RedisTransKey.setForkBlogKey(entity.getUserid())
                ,.1,10, TimeUnit.MINUTES
        );
        return R.ok(backMessage);
    }

    @Override
    public R viewPull() {
        return null;
    }

    @Override
    public R acceptMerge() {
        return null;
    }
}
