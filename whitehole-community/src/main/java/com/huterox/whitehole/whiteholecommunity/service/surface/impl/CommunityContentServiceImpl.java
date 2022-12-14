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
        //?????????????????????id
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
        //?????????????????????
        BlogsEntity communityBlogEntity = blogsService.getById(blogid);
        if(communityBlogEntity==null){
            return R.error(BizCodeEnum.BAD_SOURCE.getCode(),BizCodeEnum.BAD_AUTHORITY.getMsg());
        }
        if(!communityBlogEntity.getCommunityid().equals(communityid)){
            String ipAddr = GetIPAddrUtils.GetIPAddr();
            redisUtils.set(RedisTransKey.setACBadAuthority(ipAddr),1,10, TimeUnit.MINUTES);
            return R.error(BizCodeEnum.BAD_AUTHORITY.getCode(),BizCodeEnum.BAD_AUTHORITY.getMsg());
        }
        //??????Blog????????????????????????ok???
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

        //???????????????????????????????????????????????????????????????????????????????????????
        communityBlogEntity.setStatus(1);
        blogsService.updateById(communityBlogEntity);
        //?????????????????????
        blogEntity.setStatus(1);
        feignBlogService.update(blogEntity);
        //?????????????????????

        HoleAduitMsgQ holeAduitMsgQ = new HoleAduitMsgQ();
        holeAduitMsgQ.setMsgtitle("????????????????????????");
        holeAduitMsgQ.setMsg("????????????"+community.getCommunityTitle()+"?????????????????????");
        holeAduitMsgQ.setUserid(blogEntity.getUserid());
        holeAduitMsgQ.setLinkid(String.valueOf(blogEntity.getBlogid()));
//        1-?????????2-?????????3-?????????4-pushAc???5-Merge???6-?????????7-??????????????????
        holeAduitMsgQ.setType(1);
        feignHoleAduitMsgService.holeAduitMsg(holeAduitMsgQ);
        redisUtils.set(RedisTransKey.setCommunityAcBlog(community.getUserid())
                ,1,10, TimeUnit.SECONDS
        );
        //??????????????????
        community.setBlogNumber(community.getBlogNumber()+1);
        communityService.updateById(community);
        return R.ok("????????????");
    }

    @Override
    public R AcQuiz(AcQuizQ acQuizQ) {
        /*
        * ???????????????????????????????????????????????????
        * */
        Long quizid = acQuizQ.getQuizid();
        String userid = acQuizQ.getUserid();
        Long communityid = acQuizQ.getCommunityid();
        if(redisUtils.hasKey(RedisTransKey.getCommunityAcQuiz(userid))){
            return R.error(BizCodeEnum.TOO_FASTAUTHORITY.getCode(), BizCodeEnum.TOO_FASTAUTHORITY.getMsg());
        }
        //?????????????????????
        CommunityEntity community = communityService.getById(communityid);
        R r = this.communityOK(community,userid);
        if(r!=null){
            return r;
        }
        //?????????????????????
        QuizsEntity quizsEntity = quizsService.getById(quizid);
        if(quizsEntity==null){
            return R.error(BizCodeEnum.BAD_SOURCE.getCode(),BizCodeEnum.BAD_AUTHORITY.getMsg());
        }
        if(!quizsEntity.getCommunityid().equals(communityid)){
            String ipAddr = GetIPAddrUtils.GetIPAddr();
            redisUtils.set(RedisTransKey.setACBadAuthority(ipAddr),1,10, TimeUnit.MINUTES);
            return R.error(BizCodeEnum.BAD_AUTHORITY.getCode(),BizCodeEnum.BAD_AUTHORITY.getMsg());
        }

        //??????Quiz????????????????????????ok???
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

        //????????????????????????????????????
        quizsEntity.setStatus(1);
        quizsService.save(quizsEntity);
        quizEntity.setStatus(1);
        feignQuizService.save(quizEntity);

        //??????????????????
        HoleAduitMsgQ holeAduitMsgQ = new HoleAduitMsgQ();
        holeAduitMsgQ.setMsgtitle("????????????????????????");
        holeAduitMsgQ.setMsg("????????????"+community.getCommunityTitle()+"?????????????????????");
        holeAduitMsgQ.setUserid(quizEntity.getUserid());
        holeAduitMsgQ.setLinkid(String.valueOf(quizEntity.getQuizid()));
//        1-?????????2-?????????3-?????????4-pushAc???5-Merge???6-?????????7-??????????????????
        holeAduitMsgQ.setType(2);
        feignHoleAduitMsgService.holeAduitMsg(holeAduitMsgQ);
        redisUtils.set(RedisTransKey.setCommunityAcQuiz(community.getUserid())
                ,1,10, TimeUnit.SECONDS
        );
        return R.ok("????????????");
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
        //??????ans???????????????????????????????????????????????????????????????Quiz??????????????????
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
        //??????????????????????????????
        ansEntity.setStatus(1);
        feignAnsService.update(ansEntity);
        //??????????????????
        HoleAduitMsgQ holeAduitMsgQ = new HoleAduitMsgQ();
        holeAduitMsgQ.setMsgtitle("????????????????????????");
        holeAduitMsgQ.setMsg("????????????"+community.getCommunityTitle()+"?????????????????????");
        holeAduitMsgQ.setUserid(ansEntity.getUserid());
        holeAduitMsgQ.setLinkid(String.valueOf(ansEntity.getAnsid()));
//        1-?????????2-?????????3-?????????4-pushAc???5-Merge???6-?????????7-??????????????????
        holeAduitMsgQ.setType(3);
        feignHoleAduitMsgService.holeAduitMsg(holeAduitMsgQ);
        redisUtils.set(RedisTransKey.setCommunityAcAns(community.getUserid())
                ,1,10, TimeUnit.SECONDS
        );
        return R.ok("????????????");
    }

    public R communityOK(CommunityEntity community,String userid){
        /*
         * ???????????????????????????????????????????????????????????????
         * ??????????????????????????????????????????????????????IP
         * ????????????????????????????????????
         * */
        if(community==null){
            return R.error(BizCodeEnum.NO_SUCHCOMMUNITY.getCode(),BizCodeEnum.NO_SUCHCOMMUNITY.getMsg());
        }
        if(!community.getUserid().equals(userid)){
            //??????????????????????????????
            String ipAddr = GetIPAddrUtils.GetIPAddr();
            redisUtils.set(RedisTransKey.setACBadAuthority(ipAddr),1,10, TimeUnit.MINUTES);
            return R.error(BizCodeEnum.BAD_AUTHORITY.getCode(),BizCodeEnum.BAD_AUTHORITY.getMsg());
        }
        return null;
    }
}
