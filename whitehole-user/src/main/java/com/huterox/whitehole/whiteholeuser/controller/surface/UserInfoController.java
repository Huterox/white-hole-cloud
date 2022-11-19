package com.huterox.whitehole.whiteholeuser.controller.surface;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholeuser.dao.CommunityJoinDao;
import com.huterox.whitehole.whiteholeuser.entity.surface.userinfo.UserInfoListQueryEntity;
import com.huterox.whitehole.whiteholeuser.service.base.CommunityJoinService;
import com.huterox.whitehole.whiteholeuser.service.surface.UserInfoService;
import com.huterox.whiteholecould.entity.user.CommunityJoinEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    CommunityJoinDao communityJoinDao;

    @RequestMapping("/userinfo")
    public R userInfo(@RequestParam(value="userid") String userid){
        return userInfoService.userInfo(userid);
    }
    @RequestMapping("/userinfo/userarticle")
    public R userInfoArticle(UserInfoListQueryEntity entity){
        return userInfoService.userInfoArticle(entity);
    }
    @RequestMapping("/userinfo/userquizList")
    public R userInfoQuiz(UserInfoListQueryEntity entity){
        return userInfoService.userInfoQuiz(entity);
    }
    @RequestMapping("/userinfo/useransList")
    public R userInfoAns(UserInfoListQueryEntity entity){
        return userInfoService.userInfoAns(entity);
    }
    @RequestMapping("/userinfo/userunityList")
    public R userInfoUnity(UserInfoListQueryEntity entity){
        return userInfoService.userInfoUnity(entity);
    }

    @RequestMapping("/userinfo/communityisIn")
    public R communityisIn(@RequestParam Map<String, Object> params){
        String userid = (String) params.get("userid");
        Long communityid = Long.valueOf((String) params.get("communityid"));
        QueryWrapper<CommunityJoinEntity> communityJoinEntityQueryWrapper = new QueryWrapper<>();
        communityJoinEntityQueryWrapper.eq("communityid",communityid)
                .eq("userid",userid);
        CommunityJoinEntity communityJoinEntity = communityJoinDao.selectOne(communityJoinEntityQueryWrapper);
        return R.ok().put("in",communityJoinEntity);
    }

}
