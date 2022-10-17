package com.huterox.whitehole.whiteholeuser.service.base.impl;

import com.huterox.whiteholecould.entity.user.BlogForkEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholeuser.dao.BlogForkDao;
import com.huterox.whitehole.whiteholeuser.service.base.BlogForkService;


@Service("blogForkService")
public class BlogForkServiceImpl extends ServiceImpl<BlogForkDao, BlogForkEntity> implements BlogForkService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String key = (String) params.get("key");
        String accurate = (String) params.get("accurate");
        IPage<BlogForkEntity> page_params = new Query<BlogForkEntity>().getPage(params);
        QueryWrapper<BlogForkEntity> blogForkEntityQueryWrapper = new QueryWrapper<>();
        
        if(key!=null){
            if(accurate==null){
                //此时表示只有key,没有accurate，说明是后台管理系统在调用
                blogForkEntityQueryWrapper.like("userid",key).or().
                        like("articleid",key).or().
                        like("article_title",key);
            }else {
                //此时有accurate说明是用户端在调用
                //这里没有那些level，status，被下架了就直接访问不了
                if(accurate.equals("single")){
                    String table_name = (String) params.get("table_name");
                    String order = (String) params.get("order");
                    blogForkEntityQueryWrapper.eq(table_name,key);
                    if(order.equals("desc")){
                        blogForkEntityQueryWrapper.orderByDesc("articleid");
                    }
                }else if(accurate.equals("many")){
                    Object accurate_query = params.get("accurate_query");
                    BeanUtils.copyProperties(accurate_query,blogForkEntityQueryWrapper);
                }
            }
        }
        IPage<BlogForkEntity> page = this.page(
                page_params,
                blogForkEntityQueryWrapper
        );

        return new PageUtils(page);
    }

}