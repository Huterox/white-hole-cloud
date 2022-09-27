package com.huterox.whiteholecould.feign.quiz;


import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.quiz.AnsComComentLikeEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


/**
 * 这个表就是单纯的记录一下谁给这个在回答的下面的评论当中的评论点赞了（准确的说是评论的回复）
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
@FeignClient("quiz")
@RequestMapping("whiteholequiz/anscomcomentlike")
public interface FeignAnsComComentLikeService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholequiz:anscomcomentlike:list")
    public R list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/info/{userid}")
    //RequiresPermissions("whiteholequiz:anscomcomentlike:info")
    public R info(@PathVariable("userid") String userid);
    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholequiz:anscomcomentlike:save")
    public R save(@RequestBody AnsComComentLikeEntity ansComComentLike);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholequiz:anscomcomentlike:update")
    public R update(@RequestBody AnsComComentLikeEntity ansComComentLike);
    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholequiz:anscomcomentlike:delete")
    public R delete(@RequestBody String[] userids);

}
