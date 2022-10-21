package com.huterox.whitehole.whiteholequiz.controller.base;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.huterox.whiteholecould.entity.quiz.AnsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huterox.whitehole.whiteholequiz.service.base.AnsService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 问题的回答
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
@RestController
@RequestMapping("whiteholequiz/ans")
public class AnsController {
    @Autowired
    private AnsService ansService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholequiz:ans:list")
    public R list(@RequestParam Map<String, Object> params) throws Exception {
        PageUtils page = ansService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{ansid}")
    //RequiresPermissions("whiteholequiz:ans:info")
    public R info(@PathVariable("ansid") Long ansid){
		AnsEntity ans = ansService.getById(ansid);

        return R.ok().put("ans", ans);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholequiz:ans:save")
    public R save(@RequestBody AnsEntity ans){
		ansService.save(ans);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholequiz:ans:update")
    public R update(@RequestBody AnsEntity ans){
		ansService.updateById(ans);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholequiz:ans:delete")
    public R delete(@RequestBody Long[] ansids){
		ansService.removeByIds(Arrays.asList(ansids));

        return R.ok();
    }

}
