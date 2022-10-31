package com.huterox.whitehole.whiteholeblog.service.surface.impl;
import com.huterox.common.utils.DateUtils;
import com.huterox.common.utils.FastJsonUtils;
import com.huterox.common.utils.R;
import com.huterox.common.wordfilter.WordFilter;
import com.huterox.whitehole.whiteholeblog.entity.surface.blogUp.UpBlogEntity;
import com.huterox.whitehole.whiteholeblog.exception.BizCodeEnum;
import com.huterox.whitehole.whiteholeblog.service.base.BlogService;
import com.huterox.whitehole.whiteholeblog.service.base.ContentService;
import com.huterox.whitehole.whiteholeblog.service.surface.BlogUpService;
import com.huterox.whitehole.whiteholeblog.utils.RedisTransKey;
import com.huterox.whitehole.whiteholeblog.utils.RedisUtils;
import com.huterox.whiteholecould.entity.blog.BlogEntity;
import com.huterox.whiteholecould.entity.blog.ContentEntity;
import com.huterox.whiteholecould.entity.message.Q.HoleAduitMsgQ;
import com.huterox.whiteholecould.entity.user.HeadimgEntity;
import com.huterox.whiteholecould.entity.user.LogActicleEntity;
import com.huterox.whiteholecould.entity.user.UserEntity;
import com.huterox.whiteholecould.feign.message.FeignHoleAduitMsgService;
import com.huterox.whiteholecould.feign.user.FeignHeadimgService;
import com.huterox.whiteholecould.feign.user.FeignLogActicleService;
import com.huterox.whiteholecould.feign.user.FeignUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class BlogUpServiceImpl implements BlogUpService {

    @Autowired
    FeignUserService feignUserService;
    @Autowired
    ContentService contentService;
    @Autowired
    FeignHeadimgService feignHeadimgService;
    @Autowired
    WordFilter wordFilter;
    @Autowired
    BlogService blogService;
    @Autowired
    FeignLogActicleService feignLogActicleService;
    @Autowired
    RedisUtils redisUtils;

    @Autowired
    FeignHoleAduitMsgService feignHoleAduitMsgService;

    private final static Double threshold = 0.05;
    /**
     * 接口对用户进行十分钟限制
     *  1.完成用户博文的上传
     *  2.存储用户博文，博文对应信息
     *  3.修改用户日志
     * */
    @Override
    public R blogUp(UpBlogEntity entity) {
        String userid = entity.getUserid();
        String backMessage = "success";
        //接口限流
        if(redisUtils.hasKey(RedisTransKey.getBlogUpKey(entity.getUserid()))){
            return R.error(BizCodeEnum.OVER_UPBLOG.getCode(), BizCodeEnum.OVER_UPBLOG.getMsg());
        }
        R info = feignUserService.info(userid);
        String userString = FastJsonUtils.toJson(info.get("user"));
        UserEntity user = FastJsonUtils.fromJson(userString, UserEntity.class);
        if(user!=null){
            String context = entity.getContext();
            String blogInfo = entity.getInfo();
            /**
             * 先对context和bloginfo进行校验，是否为存在不友好的信息
             * */
            int countContext = wordFilter.wordCount(context);
            int countInfo = wordFilter.wordCount(blogInfo);
            int status = 1;
            //博文的摘要过滤,只要摘要没有过，直接先打回去！
            if(countInfo>=blogInfo.length()*threshold){
                return R.error(BizCodeEnum.BAD_BLOGINFO.getCode(),BizCodeEnum.BAD_BLOGINFO.getMsg());
            }
            //博文内容的过滤
            if(countContext>=context.length()*threshold){
                //直接就是没有通过审核
                return R.error(BizCodeEnum.BAD_CONTEXT.getCode(),BizCodeEnum.BAD_CONTEXT.getMsg());
            }else if (countContext>0&&countContext<context.length()*threshold){
                backMessage="哇！您的提交直接通过了呢！";
            }else {
                status = 2;
                context = wordFilter.replace(context, '*');
                backMessage="您的提问已提交，正在等待审核哟！";
            }
            //预存储content
            ContentEntity contentEntity = new ContentEntity();
            contentEntity.setContent(context);
            contentEntity.setVersion("1.0");
            contentEntity.setCreateTime(DateUtils.getCurrentTime());
            contentService.save(contentEntity);
            Long contentid = contentEntity.getContentid();
            //预存储博文
            BlogEntity blogEntity = new BlogEntity();
            blogEntity.setBlogTitle(entity.getBlogTitle());
            blogEntity.setLevel(entity.getLevel());
            blogEntity.setBlogtype(entity.getBlogtype());
            //查询用户的头像信息
            R RHeadImg = feignHeadimgService.headimg(userid);
            String headImgString = FastJsonUtils.toJson(RHeadImg.get("headimg"));
            final HeadimgEntity headimg = FastJsonUtils.fromJson(headImgString, HeadimgEntity.class);
            if(headimg!=null){
                blogEntity.setUserImg(headimg.getImgpath());
            }
            blogEntity.setCreateTime(DateUtils.getCurrentTime());
            blogEntity.setUserNickname(user.getNickname());
            blogEntity.setUserid(userid);
            blogEntity.setStatus(status);
            blogEntity.setInfo(blogInfo);
            blogService.save(blogEntity);
            Long blogid = blogEntity.getBlogid();

            //完成正式存储
            contentEntity.setBlogid(blogid);
            blogEntity.setContentid(contentid);
            blogService.updateById(blogEntity);
            contentService.updateById(contentEntity);
            /**
             * 更新用户日志
             * */
            LogActicleEntity logActicleEntity = new LogActicleEntity();
            logActicleEntity.setAction(1);
            logActicleEntity.setUserid(userid);
            logActicleEntity.setArticleid(blogEntity.getBlogid());
            logActicleEntity.setArticleTitle(blogEntity.getBlogTitle());
            logActicleEntity.setCreteTime(blogEntity.getCreateTime());
            feignLogActicleService.save(logActicleEntity);

            /**
             * 发送消息
             * */
            if(status==1){
                /**
                 * 此时是直接通过了审核，那么直接进行发送
                 * 如果没有的话，那么就是后台通过审核由MQ发送消息
                 * */
                HoleAduitMsgQ holeAduitMsgQ = new HoleAduitMsgQ();
                holeAduitMsgQ.setMsg("您的博文"+blogEntity.getBlogTitle()+"直接通过了审核");
                holeAduitMsgQ.setMsgtitle("博文审核通过");
                holeAduitMsgQ.setUserid(user.getUserid());
                holeAduitMsgQ.setLinkid(blogid);
                holeAduitMsgQ.setType(1);
                feignHoleAduitMsgService.holeAduitMsg(holeAduitMsgQ);
            }

            /**
             * 设置标志
             */
            redisUtils.set(RedisTransKey.setBlogUpKey(entity.getUserid())
                    ,1,10, TimeUnit.MINUTES
            );
        }else{
            return R.error(BizCodeEnum.NO_SUCHUSER.getCode(),BizCodeEnum.NO_SUCHUSER.getMsg());
        }
        return R.ok(backMessage);
    }

    @Override
    public R blogUpdata() {
        return null;
    }
}
