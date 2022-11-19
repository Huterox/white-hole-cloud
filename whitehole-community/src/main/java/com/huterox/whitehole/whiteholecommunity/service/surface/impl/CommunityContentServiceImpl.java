package com.huterox.whitehole.whiteholecommunity.service.surface.impl;
import com.huterox.common.utils.FastJsonUtils;
import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholecommunity.entity.surface.communityAcQ.AcAnsQ;
import com.huterox.whitehole.whiteholecommunity.entity.surface.communityAcQ.AcBlogQ;
import com.huterox.whitehole.whiteholecommunity.entity.surface.communityAcQ.AcQuizQ;
import com.huterox.whitehole.whiteholecommunity.exception.BizCodeEnum;
import com.huterox.whitehole.whiteholecommunity.service.base.BlogsService;
import com.huterox.whitehole.whiteholecommunity.service.base.CommunityService;
import com.huterox.whitehole.whiteholecommunity.service.base.QuizsService;
import com.huterox.whitehole.whiteholecommunity.service.surface.CommunityContentAcService;
import com.huterox.whitehole.whiteholecommunity.utils.GetIPAddrUtils;
import com.huterox.whitehole.whiteholecommunity.utils.RedisTransKey;
import com.huterox.whitehole.whiteholecommunity.utils.RedisUtils;
import com.huterox.whiteholecould.entity.blog.BlogEntity;
import com.huterox.whiteholecould.entity.community.BlogsEntity;
import com.huterox.whiteholecould.entity.community.CommunityEntity;
import com.huterox.whiteholecould.entity.community.QuizsEntity;
import com.huterox.whiteholecould.entity.message.Q.HoleAduitMsgQ;
import com.huterox.whiteholecould.entity.quiz.AnsEntity;
import com.huterox.whiteholecould.entity.quiz.QuizEntity;
import com.huterox.whiteholecould.feign.blog.FeignBlogService;
import com.huterox.whiteholecould.feign.message.FeignHoleAduitMsgService;
import com.huterox.whiteholecould.feign.quiz.FeignAnsService;
import com.huterox.whiteholecould.feign.quiz.FeignQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CommunityContentServiceImpl implements CommunityContentAcService {

    @Autowired
    CommunityService communityService;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    BlogsService blogsService;

    @Autowired
    FeignBlogService feignBlogService;

    @Autowired
    FeignHoleAduitMsgService feignHoleAduitMsgService;

    @Autowired
    QuizsService quizsService;

    @Autowired
    FeignQuizService feignQuizService;

    @Autowired
    FeignAnsService feignAnsService;

    @Override
    public R AcBlog(AcBlogQ acBlogQ) {
        //这个是管理员的id
        String userid = acBlogQ.getUserid();
        Long communityid = acBlogQ.getCommunityid();
        Long blogid = acBlogQ.getBlogid();
        if(redisUtils.hasKey(RedisTransKey.getCommunityAcBlog(userid))){
            return R.error(BizCodeEnum.TOO_FASTAUTHORITY.getCode(), BizCodeEnum.TOO_FASTAUTHORITY.getMsg());
        }
        CommunityEntity community = communityService.getById(communityid);
        R r = this.communityOK(community,userid);
        if(r!=null){
            return r;
        }
        //对文章进行验证
        BlogsEntity communityBlogEntity = blogsService.getById(blogid);
        if(communityBlogEntity==null){
            return R.error(BizCodeEnum.BAD_SOURCE.getCode(),BizCodeEnum.BAD_AUTHORITY.getMsg());
        }
        if(!communityBlogEntity.getCommunityid().equals(communityid)){
            String ipAddr = GetIPAddrUtils.GetIPAddr();
            redisUtils.set(RedisTransKey.setACBadAuthority(ipAddr),1,10, TimeUnit.MINUTES);
            return R.error(BizCodeEnum.BAD_AUTHORITY.getCode(),BizCodeEnum.BAD_AUTHORITY.getMsg());
        }
        //调用Blog服务确定那边也是ok的
        R blog = feignBlogService.info(blogid);
        String blogString = FastJsonUtils.toJson(blog.get("blog"));
        BlogEntity blogEntity = FastJsonUtils.fromJson(blogString, BlogEntity.class);
        if(blogEntity==null){
            return R.error(BizCodeEnum.BAD_SOURCE.getCode(),BizCodeEnum.BAD_AUTHORITY.getMsg());
        }
        if(!blogEntity.getCommunityid().equals(communityid)){
            String ipAddr = GetIPAddrUtils.GetIPAddr();
            redisUtils.set(RedisTransKey.setACBadAuthority(ipAddr),1,10, TimeUnit.MINUTES);
            return R.error(BizCodeEnum.BAD_AUTHORITY.getCode(),BizCodeEnum.BAD_AUTHORITY.getMsg());
        }

        //此时通过了基本的安全校验，那么这个时候的话就可以正式服务了
        communityBlogEntity.setStatus(1);
        blogsService.updateById(communityBlogEntity);
        //更新博文服务的
        blogEntity.setStatus(1);
        feignBlogService.update(blogEntity);
        //通知对应的用户

        HoleAduitMsgQ holeAduitMsgQ = new HoleAduitMsgQ();
        holeAduitMsgQ.setMsgtitle("您的博文通过审核");
        holeAduitMsgQ.setMsg("您发布在"+community.getCommunityTitle()+"的博文通过审核");
        holeAduitMsgQ.setUserid(blogEntity.getUserid());
        holeAduitMsgQ.setLinkid(String.valueOf(blogEntity.getBlogid()));
//        1-文章，2-提问，3-回答，4-pushAc，5-Merge，6-成员，7-成员请求成功
        holeAduitMsgQ.setType(1);
        feignHoleAduitMsgService.holeAduitMsg(holeAduitMsgQ);
        redisUtils.set(RedisTransKey.setCommunityAcBlog(community.getUserid())
                ,1,10, TimeUnit.SECONDS
        );
        //更新一下状态
        community.setBlogNumber(community.getBlogNumber()+1);
        communityService.updateById(community);
        return R.ok("审核完成");
    }

    @Override
    public R AcQuiz(AcQuizQ acQuizQ) {
        /*
        * 这里的大体流程和审核博文的是一样的
        * */
        Long quizid = acQuizQ.getQuizid();
        String userid = acQuizQ.getUserid();
        Long communityid = acQuizQ.getCommunityid();
        if(redisUtils.hasKey(RedisTransKey.getCommunityAcQuiz(userid))){
            return R.error(BizCodeEnum.TOO_FASTAUTHORITY.getCode(), BizCodeEnum.TOO_FASTAUTHORITY.getMsg());
        }
        //对社区进行验证
        CommunityEntity community = communityService.getById(communityid);
        R r = this.communityOK(community,userid);
        if(r!=null){
            return r;
        }
        //对提问进行验证
        QuizsEntity quizsEntity = quizsService.getById(quizid);
        if(quizsEntity==null){
            return R.error(BizCodeEnum.BAD_SOURCE.getCode(),BizCodeEnum.BAD_AUTHORITY.getMsg());
        }
        if(!quizsEntity.getCommunityid().equals(communityid)){
            String ipAddr = GetIPAddrUtils.GetIPAddr();
            redisUtils.set(RedisTransKey.setACBadAuthority(ipAddr),1,10, TimeUnit.MINUTES);
            return R.error(BizCodeEnum.BAD_AUTHORITY.getCode(),BizCodeEnum.BAD_AUTHORITY.getMsg());
        }

        //调用Quiz服务确定那边也是ok的
        R quiz = feignQuizService.info(quizid);
        String quizString = FastJsonUtils.toJson(quiz.get("quiz"));
        QuizEntity quizEntity = FastJsonUtils.fromJson(quizString, QuizEntity.class);
        if(quizEntity==null){
            return R.error(BizCodeEnum.BAD_SOURCE.getCode(),BizCodeEnum.BAD_AUTHORITY.getMsg());
        }
        if(!quizEntity.getCommunityid().equals(communityid)){
            String ipAddr = GetIPAddrUtils.GetIPAddr();
            redisUtils.set(RedisTransKey.setACBadAuthority(ipAddr),1,10, TimeUnit.MINUTES);
            return R.error(BizCodeEnum.BAD_AUTHORITY.getCode(),BizCodeEnum.BAD_AUTHORITY.getMsg());
        }

        //所有的验证通过，完成审核
        quizsEntity.setStatus(1);
        quizsService.save(quizsEntity);
        quizEntity.setStatus(1);
        feignQuizService.save(quizEntity);

        //进行消息通知
        HoleAduitMsgQ holeAduitMsgQ = new HoleAduitMsgQ();
        holeAduitMsgQ.setMsgtitle("您的提问通过审核");
        holeAduitMsgQ.setMsg("您发布在"+community.getCommunityTitle()+"的提问通过审核");
        holeAduitMsgQ.setUserid(quizEntity.getUserid());
        holeAduitMsgQ.setLinkid(String.valueOf(quizEntity.getQuizid()));
//        1-文章，2-提问，3-回答，4-pushAc，5-Merge，6-成员，7-成员请求成功
        holeAduitMsgQ.setType(2);
        feignHoleAduitMsgService.holeAduitMsg(holeAduitMsgQ);
        redisUtils.set(RedisTransKey.setCommunityAcQuiz(community.getUserid())
                ,1,10, TimeUnit.SECONDS
        );
        return R.ok("审核完成");
    }

    @Override
    public R AcAns(AcAnsQ acAnsQ) {
        String userid = acAnsQ.getUserid();
        Long ansid = acAnsQ.getAnsid();
        Long communityid = acAnsQ.getCommunityid();
        if(redisUtils.hasKey(RedisTransKey.getCommunityAcQuiz(userid))){
            return R.error(BizCodeEnum.TOO_FASTAUTHORITY.getCode(), BizCodeEnum.TOO_FASTAUTHORITY.getMsg());
        }

        CommunityEntity community = communityService.getById(communityid);
        R r = this.communityOK(community,userid);
        if(r!=null){
            return r;
        }
        //这个ans在社区是没有确定备份的，所以的话，直接访问Quiz服务就可以了
        R ans = feignQuizService.info(ansid);
        String ansString = FastJsonUtils.toJson(ans.get("ans"));
        AnsEntity ansEntity = FastJsonUtils.fromJson(ansString, AnsEntity.class);
        if(ansEntity==null){
            return R.error(BizCodeEnum.BAD_SOURCE.getCode(),BizCodeEnum.BAD_AUTHORITY.getMsg());
        }
        if(!ansEntity.getCommunityid().equals(communityid)){
            String ipAddr = GetIPAddrUtils.GetIPAddr();
            redisUtils.set(RedisTransKey.setACBadAuthority(ipAddr),1,10, TimeUnit.MINUTES);
            return R.error(BizCodeEnum.BAD_AUTHORITY.getCode(),BizCodeEnum.BAD_AUTHORITY.getMsg());
        }
        //此时完成了最终的验证
        ansEntity.setStatus(1);
        feignAnsService.update(ansEntity);
        //进行消息通知
        HoleAduitMsgQ holeAduitMsgQ = new HoleAduitMsgQ();
        holeAduitMsgQ.setMsgtitle("您的回答通过审核");
        holeAduitMsgQ.setMsg("您发布在"+community.getCommunityTitle()+"的回答通过审核");
        holeAduitMsgQ.setUserid(ansEntity.getUserid());
        holeAduitMsgQ.setLinkid(String.valueOf(ansEntity.getAnsid()));
//        1-文章，2-提问，3-回答，4-pushAc，5-Merge，6-成员，7-成员请求成功
        holeAduitMsgQ.setType(3);
        feignHoleAduitMsgService.holeAduitMsg(holeAduitMsgQ);
        redisUtils.set(RedisTransKey.setCommunityAcAns(community.getUserid())
                ,1,10, TimeUnit.SECONDS
        );
        return R.ok("审核完成");
    }

    public R communityOK(CommunityEntity community,String userid){
        /*
         * 对社区进行验证，如果是没有，那么就提示没有
         * 如果是信息对不上，直接对当前服务封锁IP
         * 当一切正常的时候返回为空
         * */
        if(community==null){
            return R.error(BizCodeEnum.NO_SUCHCOMMUNITY.getCode(),BizCodeEnum.NO_SUCHCOMMUNITY.getMsg());
        }
        if(!community.getUserid().equals(userid)){
            //此时可能出现越权操作
            String ipAddr = GetIPAddrUtils.GetIPAddr();
            redisUtils.set(RedisTransKey.setACBadAuthority(ipAddr),1,10, TimeUnit.MINUTES);
            return R.error(BizCodeEnum.BAD_AUTHORITY.getCode(),BizCodeEnum.BAD_AUTHORITY.getMsg());
        }
        return null;
    }
}
