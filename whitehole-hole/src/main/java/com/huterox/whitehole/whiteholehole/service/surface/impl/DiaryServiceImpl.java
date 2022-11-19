package com.huterox.whitehole.whiteholehole.service.surface.impl;

import com.huterox.common.utils.DateUtils;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholehole.entity.surface.diary.DiaryQ;
import com.huterox.whitehole.whiteholehole.entity.surface.diary.DiaryUpQ;
import com.huterox.whitehole.whiteholehole.exception.BizCodeEnum;
import com.huterox.whitehole.whiteholehole.service.base.LogLogService;
import com.huterox.whitehole.whiteholehole.service.surface.DiaryService;
import com.huterox.whitehole.whiteholehole.utils.RedisTransKey;
import com.huterox.whitehole.whiteholehole.utils.RedisUtils;
import com.huterox.whiteholecould.entity.hole.LogLogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;


@Service
public class DiaryServiceImpl implements DiaryService {


    LogLogService logLogService;
    RedisUtils redisUtils;

    @Autowired
    public void setRedisUtils(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    @Autowired
    public void setLogLogService(LogLogService logLogService) {
        this.logLogService = logLogService;
    }

    @Override
    public R diaryList(DiaryQ diaryQ) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        params.put("page",String.valueOf(diaryQ.getPage()));
        params.put("limit",String.valueOf(diaryQ.getLimit()));
        params.put("accurate","single");
        params.put("table_name","userid");
        params.put("order","desc");
        PageUtils page = logLogService.queryPage(params);
        return R.ok().put("page", page);
    }

    @Override
    public R upDiary(DiaryUpQ diaryUpQ) {
        /*
        这里完成我们的一个diary的一个上传
        * */
        String userid = diaryUpQ.getUserid();
        if(redisUtils.hasKey(RedisTransKey.getDiaryUpKey(userid))){
            return R.error(BizCodeEnum.UP_DIARY_OVER.getCode(),BizCodeEnum.UP_DIARY_OVER.getMsg());
        }
        //这要能够通过token验证基本上userid是存在的，所以
        //这里的话就不去验证用户到底是不是真的存在
        LogLogEntity logLogEntity = new LogLogEntity();
        logLogEntity.setContent(diaryUpQ.getContent());
        logLogEntity.setUserid(userid);
        logLogEntity.setCreateTime(DateUtils.getCurrentTime());

        logLogService.save(logLogEntity);

        redisUtils.set(RedisTransKey.setDiaryUpKey(userid),1,3, TimeUnit.HOURS);

        return R.ok();
    }
}
