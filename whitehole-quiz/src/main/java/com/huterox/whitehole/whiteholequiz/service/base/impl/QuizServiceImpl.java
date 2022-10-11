package com.huterox.whitehole.whiteholequiz.service.base.impl;

import com.huterox.whiteholecould.entity.blog.BlogEntity;
import com.huterox.whiteholecould.entity.quiz.QuizEntity;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.Query;

import com.huterox.whitehole.whiteholequiz.dao.QuizDao;
import com.huterox.whitehole.whiteholequiz.service.base.QuizService;


@Service("quizService")
public class QuizServiceImpl extends ServiceImpl<QuizDao, QuizEntity> implements QuizService {

    /**
     * 在所有的基础方法当中我们做出如下约定，（因为有大量的请求是需要使用到分页查询的，并且这个方法相当重要）
     * key,表示需要模糊查询或者精确查询的值，和 accurate 相互配合。
     * accurate表示改查询需要进行精确查询 当accurate=single 表示精确查询，需要指定
     * table_name 还有order:desc,asc当为many，表示需要更加复杂的查询，此时需要附带 accurate_query 即查询QueryWrapper
     * 所有的附加值都需要具备
     * 最后必须参数为
     * 'page': 第几页
     * 'limit':每页多少,
     * 此外对于用户端的查询，需要指明status，和 level否则处理将失败
     * 对Status的描述 问题的状态，1-正常，2-审核，3-审核失败，4-下架
     * */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        String accurate = (String) params.get("accurate");
        IPage<QuizEntity> page_params = new Query<QuizEntity>().getPage(params);
        QueryWrapper<QuizEntity> quizEntityQueryWrapper = new QueryWrapper<>();
        if(key!=null){
            if(accurate==null) {
                quizEntityQueryWrapper.like("userid", key).or().
                        like("quizid", key).or().
                        like("quiz_title", key).or().
                        like("user_nickname", key);
            }else {
                //此时有accurate说明是用户端在调用
                if(accurate.equals("single")){
                    String table_name = (String) params.get("table_name");
                    String order = (String) params.get("order");
                    Integer status = Integer.valueOf((String) params.get("status"));
                    quizEntityQueryWrapper.eq(table_name,key)
                            .eq("status",status);
                    if(order.equals("desc")){
                        quizEntityQueryWrapper.orderByDesc("quizid");
                    }

                }else if(accurate.equals("many")){
                    Object accurate_query = params.get("accurate_query");
                    quizEntityQueryWrapper = (QueryWrapper<QuizEntity>) accurate_query;
                }
            }
        }
        IPage<QuizEntity> page = this.page(
                page_params,
                quizEntityQueryWrapper
        );
        return new PageUtils(page);
    }

}