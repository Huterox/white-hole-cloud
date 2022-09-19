package com.huterox.whiteholecould.feign.user;

import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.user.QuizEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.Map;


/**
 * 这个表是中间表，不存在主键。
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@FeignClient("user")
@RequestMapping("user/quiz")
public interface QuizController {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:quiz:list")
    public R list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("user:quiz:info")
    public R info(@PathVariable("userid") String userid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:quiz:save")
    public R save(@RequestBody QuizEntity quiz);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:quiz:update")
    public R update(@RequestBody QuizEntity quiz);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:quiz:delete")
    public R delete(@RequestBody String[] userids);

}
