package com.huterox.whitehole.whiteholeblog.service.base.impl;

import com.huterox.whiteholecould.entity.blog.ForkEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeblog.dao.ForkDao;

import com.huterox.whitehole.whiteholeblog.service.base.ForkService;


@Service("forkService")
public class ForkServiceImpl extends ServiceImpl<ForkDao, ForkEntity> implements ForkService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        String accurate = (String) params.get("accurate");
        IPage<ForkEntity> page_params = new Query<ForkEntity>().getPage(params);
        QueryWrapper<ForkEntity> forkEntityQueryWrapper = new QueryWrapper<>();
        
        if(key!=null){
            if(accurate==null) {
                forkEntityQueryWrapper.like("userid", key).or().
                        like("blogid", key).or().
                        like("blog_userid", key).or().
                        like("blog_title", key).or().
                        like("blog_user_nickname", key);
            }else {
                //此时有accurate说明是用户端在调用
                if(accurate.equals("single")){
                    String table_name = (String) params.get("table_name");
                    String order = (String) params.get("order");
                    Integer status = Integer.valueOf((String) params.get("status"));
                    Integer level = Integer.valueOf((String) params.get("level"));
                    forkEntityQueryWrapper.eq(table_name,key)
                            .eq("status",status)
                            .eq("level",level);
                    if(order.equals("desc")){
                        forkEntityQueryWrapper.orderByDesc("blogid");
                    }

                }else if(accurate.equals("many")){
                    Object accurate_query = params.get("accurate_query");
                    BeanUtils.copyProperties(accurate_query,forkEntityQueryWrapper);
                }
            }
        }
        IPage<ForkEntity> page = this.page(
                page_params,
                forkEntityQueryWrapper
        );

        return new PageUtils(page);
    }

}