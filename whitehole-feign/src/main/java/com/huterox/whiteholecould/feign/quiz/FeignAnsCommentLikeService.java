package com.huterox.whiteholecould.feign.quiz;


import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.quiz.AnsCommentLikeEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
@FeignClient("quiz")
@RequestMapping("whiteholequiz/anscommentlike")
public interface FeignAnsCommentLikeService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholequiz:anscommentlike:list")
    public R list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("whiteholequiz:anscommentlike:info")
    public R info(@PathVariable("userid") String userid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholequiz:anscommentlike:save")
    public R save(@RequestBody AnsCommentLikeEntity ansCommentLike);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholequiz:anscommentlike:update")
    public R update(@RequestBody AnsCommentLikeEntity ansCommentLike);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholequiz:anscommentlike:delete")
    public R delete(@RequestBody String[] userids);

}
