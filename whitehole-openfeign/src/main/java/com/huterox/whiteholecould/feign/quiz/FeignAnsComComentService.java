package com.huterox.whiteholecould.feign.quiz;

import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.quiz.AnsComComentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
@FeignClient("quiz")
@RequestMapping("whiteholequiz/anscomcoment")
public interface FeignAnsComComentService {

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholequiz:anscomcoment:list")
    public R list(@RequestParam Map<String, Object> params);

    /**
     * 信息
     */
    @RequestMapping("/info/{comComentid}")
    //RequiresPermissions("whiteholequiz:anscomcoment:info")
    public R info(@PathVariable("comComentid") Long comComentid);

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholequiz:anscomcoment:save")
    public R save(@RequestBody AnsComComentEntity ansComComent);

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholequiz:anscomcoment:update")
    public R update(@RequestBody AnsComComentEntity ansComComent);

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholequiz:anscomcoment:delete")
    public R delete(@RequestBody Long[] comComentids);

}
