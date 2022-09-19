package com.huterox.whiteholecould.feign.quiz;


import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.quiz.CollectEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


/**
 * 这个是问题的收藏，我们来看看是谁收藏了这个问题
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
@FeignClient("quiz")
@RequestMapping("whiteholequiz/collect")
public interface FeignCollectService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholequiz:collect:list")
    public R list(@RequestParam Map<String, Object> params);

    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("whiteholequiz:collect:info")
    public R info(@PathVariable("userid") String userid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholequiz:collect:save")
    public R save(@RequestBody CollectEntity collect);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholequiz:collect:update")
    public R update(@RequestBody CollectEntity collect);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholequiz:collect:delete")
    public R delete(@RequestBody String[] userids);
}
