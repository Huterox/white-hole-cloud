package com.huterox.whitehole.whiteholehole.service.base.impl;

import com.huterox.common.utils.SerializeUtil;
import com.huterox.whiteholecould.entity.hole.LogLogEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholehole.dao.LogLogDao;
import com.huterox.whitehole.whiteholehole.service.base.LogLogService;


@Service("logLogService")
public class LogLogServiceImpl extends ServiceImpl<LogLogDao, LogLogEntity> implements LogLogService {

    /**
     *
     * 在所有的基础方法当中我们做出如下约定，（因为有大量的请求是需要使用到分页查询的，并且这个方法相当重要）
     * key,表示需要模糊查询或者精确查询的值，和 accurate 相互配合。
     * accurate表示改查询需要进行精确查询 当accurate=single 表示精确查询，需要指定
     * table_name 还有order:desc,asc当为many，表示需要更加复杂的查询，此时需要附带 accurate_query 即查询QueryWrapper
     * 所有的附加值都需要具备
     * 最后必须参数为
     * 'page': 第几页
     * 'limit':每页多少,
     * 此外对于用户端的查询，需要指明status，和 level否则处理将失败
     * */

    @Override
    public PageUtils queryPage(Map<String, Object> params) throws Exception {
        String key = (String) params.get("key");
        String accurate = (String) params.get("accurate");
        IPage<LogLogEntity> page_params = new Query<LogLogEntity>().getPage(params);
        QueryWrapper<LogLogEntity> logLogEntityQueryWrapper = new QueryWrapper<>();
        if(key!=null){
            if(accurate==null){
                //此时表示只有key,没有accurate，说明是后台管理系统在调用
                logLogEntityQueryWrapper.eq("userid",key);
            }else {
                //此时有accurate说明是用户端在调用
                if(accurate.equals("single")){
                    String table_name = (String) params.get("table_name");
                    String order = (String) params.get("order");

                    logLogEntityQueryWrapper.eq(table_name,key);

                    if(order.equals("desc")){
                        logLogEntityQueryWrapper.orderByDesc("create_time");
                    }

                }else if(accurate.equals("many")){
                    Object accurate_query = params.get("accurate_query");
                    QueryWrapper<LogLogEntity> deserialize = (QueryWrapper<LogLogEntity>) SerializeUtil.deserialize(accurate_query.toString());
                    logLogEntityQueryWrapper = deserialize;
                }
            }
        }
        IPage<LogLogEntity> page = this.page(
                page_params,
                logLogEntityQueryWrapper
        );

        return new PageUtils(page);
    }


}