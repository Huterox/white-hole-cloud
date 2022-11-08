package com.huterox.whitehole.whiteholeuser.service.base.impl;

import com.huterox.common.utils.SerializeUtil;
import com.huterox.whiteholecould.entity.user.CommunityJoinEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeuser.dao.CommunityJoinDao;
import com.huterox.whitehole.whiteholeuser.service.base.CommunityJoinService;


@Service("communityJoinService")
public class CommunityJoinServiceImpl extends ServiceImpl<CommunityJoinDao, CommunityJoinEntity> implements CommunityJoinService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) throws Exception {
        String key = (String) params.get("key");
        String accurate = (String) params.get("accurate");
        IPage<CommunityJoinEntity> page_params = new Query<CommunityJoinEntity>().getPage(params);
        QueryWrapper<CommunityJoinEntity> CommunityJoinEntityQueryWrapper = new QueryWrapper<>();
        if(key!=null){
            if(accurate==null){
                //此时表示只有key,没有accurate，说明是后台管理系统在调用
                CommunityJoinEntityQueryWrapper.like("userid",key).or().
                        like("userid",key).or().
                        like("community_name",key);
            }else {
                //此时有accurate说明是用户端在调用
                if(accurate.equals("single")){
                    String table_name = (String) params.get("table_name");
                    String order = (String) params.get("order");
                    CommunityJoinEntityQueryWrapper.eq(table_name,key);
                    if(order.equals("desc")){
                        CommunityJoinEntityQueryWrapper.orderByDesc("community_join_time");
                    }
                }else if(accurate.equals("many")){
                    Object accurate_query = params.get("accurate_query");
                    QueryWrapper<CommunityJoinEntity> deserialize = (QueryWrapper<CommunityJoinEntity>) SerializeUtil.deserialize(accurate_query.toString());
                    CommunityJoinEntityQueryWrapper = deserialize;
                }
            }
        }
        IPage<CommunityJoinEntity> page = this.page(
                page_params,
                CommunityJoinEntityQueryWrapper
        );

        return new PageUtils(page);
    }

}