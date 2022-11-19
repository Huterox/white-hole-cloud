package com.huterox.whitehole.whiteholecommunity.service.base.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;
import com.huterox.common.utils.SerializeUtil;
import com.huterox.whitehole.whiteholecommunity.dao.QuizsDao;
import com.huterox.whitehole.whiteholecommunity.service.base.QuizsService;
import com.huterox.whiteholecould.entity.community.QuizsEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("quizsService")
public class QuizsServiceImpl extends ServiceImpl<QuizsDao, QuizsEntity> implements QuizsService {
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
     * 此外对于用户端的查询，需要指明 status
     * 1-正常 2-审核 3-下架 4-删除
     * */

    @Override
    public PageUtils queryPage(Map<String, Object> params) throws Exception {

        String key = (String) params.get("key");
        String accurate = (String) params.get("accurate");
        IPage<QuizsEntity> page_params = new Query<QuizsEntity>().getPage(params);
        QueryWrapper<QuizsEntity> quizsEntityQueryWrapper = new QueryWrapper<>();
        /**
         * 社区的博文的一些管理不属于后台人员管理的部分！
         * */
        if(key!=null) {
            if (accurate == null) {
                quizsEntityQueryWrapper.like("communityid", key).or().
                        like("userid", key);
            } else {
                //此时有accurate说明是用户端在调用
                if (accurate.equals("single")) {
                    String table_name = (String) params.get("table_name");
                    String order = (String) params.get("order");
                    Integer status = Integer.valueOf((String) params.get("status"));
                    if(table_name.equals("HoleNULL")){
                        quizsEntityQueryWrapper
                                .eq("status", status);
                    }else {
                        quizsEntityQueryWrapper.eq(table_name, key)
                                .eq("status", status);
                    }

                    if (order.equals("desc")) {
                        quizsEntityQueryWrapper.orderByDesc("communityid");
                    }

                } else if (accurate.equals("many")) {
                    Object accurate_query = params.get("accurate_query");
                    QueryWrapper<QuizsEntity> deserialize = (QueryWrapper<QuizsEntity>) SerializeUtil.deserialize(accurate_query.toString());
                    quizsEntityQueryWrapper = deserialize;
                }
            }
        }
        IPage<QuizsEntity> page = this.page(
                page_params,
                quizsEntityQueryWrapper
        );

        return new PageUtils(page);
    }

}