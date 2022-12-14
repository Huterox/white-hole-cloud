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
import com.huterox.whiteholecould.entity.blog.Q.CommunityBlogUpQ;
import com.huterox.whiteholecould.entity.community.BlogsEntity;
import com.huterox.whiteholecould.entity.message.Q.HoleAduitMsgQ;
import com.huterox.whiteholecould.entity.user.HeadimgEntity;
import com.huterox.whiteholecould.entity.user.LogActicleEntity;
import com.huterox.whiteholecould.entity.user.UserEntity;
import com.huterox.whiteholecould.feign.community.FeignBlogsService;
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

    @Autowired
    FeignBlogsService feignBlogsService;


    private final static Double threshold = 0.05;

    /**
     * ????????????????????????????????????
     * 1.???????????????????????????
     * 2.???????????????????????????????????????
     * 3.??????????????????
     */
    @Override
    public R blogUp(UpBlogEntity entity) {
        String userid = entity.getUserid();
        String backMessage;
        //????????????
        if (redisUtils.hasKey(RedisTransKey.getBlogUpKey(entity.getUserid()))) {
            return R.error(BizCodeEnum.OVER_UPBLOG.getCode(), BizCodeEnum.OVER_UPBLOG.getMsg());
        }
        R info = feignUserService.info(userid);
        String userString = FastJsonUtils.toJson(info.get("user"));
        UserEntity user = FastJsonUtils.fromJson(userString, UserEntity.class);
        if (user != null) {
            String context = entity.getContext();
            String blogInfo = entity.getInfo();
            /*
             * ??????context???bloginfo????????????????????????????????????????????????
             * */
            int countContext = wordFilter.wordCount(context);
            int countInfo = wordFilter.wordCount(blogInfo);
            int status = 1;
            //?????????????????????,?????????????????????????????????????????????
            if (countInfo >= blogInfo.length() * threshold) {
                return R.error(BizCodeEnum.BAD_BLOGINFO.getCode(), BizCodeEnum.BAD_BLOGINFO.getMsg());
            }
            //?????????????????????
            if (countContext >= context.length() * threshold) {
                //??????????????????????????????
                return R.error(BizCodeEnum.BAD_CONTEXT.getCode(), BizCodeEnum.BAD_CONTEXT.getMsg());
            } else if (countContext > 0 && countContext < context.length() * threshold) {
                backMessage = "???????????????????????????????????????";
            } else {
                status = 2;
                context = wordFilter.replace(context, '*');
                backMessage = "????????????????????????????????????????????????";
            }
            //?????????content
            ContentEntity contentEntity = new ContentEntity();
            contentEntity.setContent(context);
            contentEntity.setVersion("1.0");
            contentEntity.setCreateTime(DateUtils.getCurrentTime());
            contentService.save(contentEntity);
            Long contentid = contentEntity.getContentid();
            //???????????????
            BlogEntity blogEntity = new BlogEntity();
            blogEntity.setBlogTitle(entity.getBlogTitle());
            blogEntity.setLevel(entity.getLevel());
            blogEntity.setBlogtype(entity.getBlogtype());
            blogEntity.setCommunityid(-1L);
            //???????????????????????????
            R RHeadImg = feignHeadimgService.headimg(userid);
            String headImgString = FastJsonUtils.toJson(RHeadImg.get("headimg"));
            HeadimgEntity headimg = FastJsonUtils.fromJson(headImgString, HeadimgEntity.class);
            if (headimg != null) {
                blogEntity.setUserImg(headimg.getImgpath());
            }
            blogEntity.setCreateTime(DateUtils.getCurrentTime());
            blogEntity.setUserNickname(user.getNickname());
            blogEntity.setUserid(userid);
            blogEntity.setStatus(status);
            blogEntity.setInfo(blogInfo);
            blogService.save(blogEntity);
            Long blogid = blogEntity.getBlogid();

            //??????????????????
            contentEntity.setBlogid(blogid);
            blogEntity.setContentid(contentid);
            blogService.updateById(blogEntity);
            contentService.updateById(contentEntity);
            /*
             * ??????????????????
             * */
            LogActicleEntity logActicleEntity = new LogActicleEntity();
            logActicleEntity.setAction(1);
            logActicleEntity.setUserid(userid);
            logActicleEntity.setArticleid(blogEntity.getBlogid());
            logActicleEntity.setArticleTitle(blogEntity.getBlogTitle());
            logActicleEntity.setCreteTime(blogEntity.getCreateTime());
            feignLogActicleService.save(logActicleEntity);

            /*
             * ????????????
             * */
            if (status == 1) {
                /*
                 * ?????????????????????????????????????????????????????????
                 * ??????????????????????????????????????????????????????MQ????????????
                 * */
                HoleAduitMsgQ holeAduitMsgQ = new HoleAduitMsgQ();
                holeAduitMsgQ.setMsg("????????????" + blogEntity.getBlogTitle() + "?????????????????????");
                holeAduitMsgQ.setMsgtitle("??????????????????");
                holeAduitMsgQ.setUserid(user.getUserid());
                holeAduitMsgQ.setLinkid(String.valueOf(blogid));
                holeAduitMsgQ.setType(1);
                feignHoleAduitMsgService.holeAduitMsg(holeAduitMsgQ);
            }
            /*
             * ????????????
             */
            redisUtils.set(RedisTransKey.setBlogUpKey(entity.getUserid())
                    , 1, 10, TimeUnit.MINUTES
            );
        } else {
            return R.error(BizCodeEnum.NO_SUCHUSER.getCode(), BizCodeEnum.NO_SUCHUSER.getMsg());
        }
        return R.ok(backMessage);
    }

    @Override
    public R blogUpdata() {
        return null;
    }

    @Override
    public R communityBlogUp(CommunityBlogUpQ entity) {

        /*
         *  1. ???????????????????????????
         *  2. ?????????????????????
         *  3. ??????????????????????????????
         */
        String userid = entity.getUserid();
        R info = feignUserService.info(userid);
        int status = -1;
        String userString = FastJsonUtils.toJson(info.get("user"));
        UserEntity user = FastJsonUtils.fromJson(userString, UserEntity.class);
        if (user != null) {
            String context = entity.getContext();
            String blogInfo = entity.getInfo();
            /*
             * ??????context???bloginfo????????????????????????????????????????????????
             * */
            int countContext = wordFilter.wordCount(context);
            int countInfo = wordFilter.wordCount(blogInfo);
            //?????????????????????,?????????????????????????????????????????????
            if (countInfo >= blogInfo.length() * threshold) {
                return R.ok().put("status", status);
            }
            //?????????????????????
            if (countContext >= context.length() * threshold) {
                return R.ok().put("status", status);
            } else if (countContext > 0 && countContext < context.length() * threshold) {
                status = 1;
            } else {
                status = 2;
                context = wordFilter.replace(context, '*');
            }
            //?????????content
            ContentEntity contentEntity = new ContentEntity();
            contentEntity.setContent(context);
            contentEntity.setVersion("1.0");
            contentEntity.setCreateTime(DateUtils.getCurrentTime());
            contentService.save(contentEntity);
            Long contentid = contentEntity.getContentid();
            //???????????????
            BlogEntity blogEntity = new BlogEntity();
            blogEntity.setBlogTitle(entity.getBlogTitle());
            blogEntity.setLevel(entity.getLevel());
            blogEntity.setBlogtype(entity.getBlogtype());
            //???????????????????????????
            blogEntity.setCommunityid(entity.getCommunityid());

            //?????????????????????????????????
            BlogsEntity communityBlog = new BlogsEntity();
            //???????????????????????????
            R RHeadImg = feignHeadimgService.headimg(userid);
            String headImgString = FastJsonUtils.toJson(RHeadImg.get("headimg"));
            HeadimgEntity headimg = FastJsonUtils.fromJson(headImgString, HeadimgEntity.class);
            if (headimg != null) {
                blogEntity.setUserImg(headimg.getImgpath());
                communityBlog.setUserImg(headimg.getImgpath());
            }
            blogEntity.setCreateTime(DateUtils.getCurrentTime());
            blogEntity.setUserNickname(user.getNickname());
            blogEntity.setUserid(userid);
            blogEntity.setStatus(status);
            blogEntity.setInfo(blogInfo);
            blogService.save(blogEntity);
            Long blogid = blogEntity.getBlogid();

            //??????????????????
            contentEntity.setBlogid(blogid);
            blogEntity.setContentid(contentid);
            blogService.updateById(blogEntity);
            contentService.updateById(contentEntity);
            /*
             * ??????????????????
             * */
            LogActicleEntity logActicleEntity = new LogActicleEntity();
            logActicleEntity.setAction(1);
            logActicleEntity.setUserid(userid);
            logActicleEntity.setArticleid(blogEntity.getBlogid());
            logActicleEntity.setArticleTitle(blogEntity.getBlogTitle());
            logActicleEntity.setCreteTime(blogEntity.getCreateTime());
            feignLogActicleService.save(logActicleEntity);


            communityBlog.setBlogid(blogid);
            communityBlog.setCommunityid(entity.getCommunityid());
            communityBlog.setBlogTitle(blogEntity.getBlogTitle());
            communityBlog.setCreateTime(DateUtils.getCurrentTime());
            communityBlog.setUserNickname(user.getNickname());
            communityBlog.setUserId(userid);
            communityBlog.setStatus(status);
            communityBlog.setBlogImg(blogEntity.getBlogimg());
            communityBlog.setBlogInfo(blogEntity.getInfo());
            //??????
            feignBlogsService.save(communityBlog);

            /*
             * ????????????
             * */
            if (status == 1) {
                /*
                 * ?????????????????????????????????????????????????????????
                 * ??????????????????????????????????????????????????????MQ????????????
                 * */
                HoleAduitMsgQ holeAduitMsgQ = new HoleAduitMsgQ();
                holeAduitMsgQ.setMsg(
                        "????????????" + blogEntity.getBlogTitle() + "??????????????????????????????????????????"
                        +entity.getCommunityName()+"?????????"
                );
                holeAduitMsgQ.setMsgtitle("??????????????????");
                holeAduitMsgQ.setUserid(user.getUserid());
                holeAduitMsgQ.setLinkid(String.valueOf(blogid));
                holeAduitMsgQ.setType(1);
                feignHoleAduitMsgService.holeAduitMsg(holeAduitMsgQ);

                //???????????????????????????????????????????????????
                holeAduitMsgQ.setMsg(
                        user.getUsername()+"???"+entity.getCommunityName()+"???????????????" +
                                blogEntity.getBlogTitle()+
                                "?????????????????????"
                );
                holeAduitMsgQ.setMsgtitle("??????????????????");
                holeAduitMsgQ.setUserid(user.getUserid());
                holeAduitMsgQ.setLinkid(String.valueOf(blogid));
                holeAduitMsgQ.setType(1);
                feignHoleAduitMsgService.holeAduitMsg(holeAduitMsgQ);
            } else {
                /*
                 * ?????????????????????????????????????????????????????????????????????????????????????????????????????????
                 * */
                HoleAduitMsgQ holeAduitMsgQ = new HoleAduitMsgQ();
                holeAduitMsgQ.setMsg(
                        user.getUsername()+"???"+entity.getCommunityName()+"????????????" +
                                "????????????????????????????????????"
                );
                holeAduitMsgQ.setMsgtitle(entity.getCommunityName()+"??????????????????");
                holeAduitMsgQ.setUserid(entity.getCommunityUserid());
                holeAduitMsgQ.setLinkid(String.valueOf(blogid));
                holeAduitMsgQ.setType(1);
                feignHoleAduitMsgService.holeAduitMsg(holeAduitMsgQ);
            }

        }else {
            //-2?????????????????????????????????????????????????????????
            return R.ok().put("status",-2);
        }
        return R.ok().put("status",status);
    }
}

