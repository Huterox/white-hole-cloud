package com.huterox.whitehole.whiteholeuser.controller.base;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huterox.whitehole.whiteholeuser.entity.base.CollectQuizEntity;
import com.huterox.whitehole.whiteholeuser.service.base.CollectQuizService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 问题回答交流的本质还是博文的逻辑，但是区别是，问答模块具备强交流性。

用户可能收藏的是整个问题，包括问题当中的所有回答，也有可能是针对某一个问题的，其他用户的回答，因此这边还需要创建一个新的表。
专门用于存储用户收藏的问题。
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@RestController
@RequestMapping("user/collectquiz")
public class CollectQuizController {
    @Autowired
    private CollectQuizService collectQuizService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:collectquiz:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = collectQuizService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:collectquiz:info")
    public R info(@PathVariable("userid") String userid){
		CollectQuizEntity collectQuiz = collectQuizService.getById(userid);

        return R.ok().put("collectQuiz", collectQuiz);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:collectquiz:save")
    public R save(@RequestBody CollectQuizEntity collectQuiz){
		collectQuizService.save(collectQuiz);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:collectquiz:update")
    public R update(@RequestBody CollectQuizEntity collectQuiz){
		collectQuizService.updateById(collectQuiz);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:collectquiz:delete")
    public R delete(@RequestBody String[] userids){
		collectQuizService.removeByIds(Arrays.asList(userids));

        return R.ok();
    }

}
