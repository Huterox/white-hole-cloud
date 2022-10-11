package com.huterox.whitehole.whiteholequiz.controller.base;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.huterox.whiteholecould.entity.quiz.AnsComComentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huterox.whitehole.whiteholequiz.service.base.AnsComComentService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
@RestController
@RequestMapping("whiteholequiz/anscomcoment")
public class AnsComComentController {
    @Autowired
    private AnsComComentService ansComComentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholequiz:anscomcoment:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = ansComComentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{comComentid}")
    //RequiresPermissions("whiteholequiz:anscomcoment:info")
    public R info(@PathVariable("comComentid") Long comComentid){
		AnsComComentEntity ansComComent = ansComComentService.getById(comComentid);

        return R.ok().put("ansComComent", ansComComent);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholequiz:anscomcoment:save")
    public R save(@RequestBody AnsComComentEntity ansComComent){
		ansComComentService.save(ansComComent);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholequiz:anscomcoment:update")
    public R update(@RequestBody AnsComComentEntity ansComComent){
		ansComComentService.updateById(ansComComent);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholequiz:anscomcoment:delete")
    public R delete(@RequestBody Long[] comComentids){
		ansComComentService.removeByIds(Arrays.asList(comComentids));

        return R.ok();
    }

}
