package com.huterox.whiteholecould.feign.quiz;

import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.quiz.AnsCollectEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


/**
 * 这个是问题回答的收藏，有哪些用户收藏了，我们的筛选标准和我们这个的问题的标准是一样的
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
@FeignClient("quiz")
@RequestMapping("whiteholequiz/anscollect")
public interface FeignAnsCollectService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholequiz:anscollect:list")
    public R list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("whiteholequiz:anscollect:info")
    public R info(@PathVariable("userid") String userid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholequiz:anscollect:save")
    public R save(@RequestBody AnsCollectEntity ansCollect);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholequiz:anscollect:update")
    public R update(@RequestBody AnsCollectEntity ansCollect);
    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholequiz:anscollect:delete")
    public R delete(@RequestBody String[] userids);

}
