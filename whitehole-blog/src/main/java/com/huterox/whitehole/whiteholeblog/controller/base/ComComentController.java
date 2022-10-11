package com.huterox.whitehole.whiteholeblog.controller.base;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.huterox.whiteholecould.entity.blog.ComComentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huterox.whitehole.whiteholeblog.service.base.ComComentService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:21:46
 */
@RestController
@RequestMapping("whiteholeblog/comcoment")
public class ComComentController {
    @Autowired
    private ComComentService comComentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("whiteholeblog:comcoment:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = comComentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{comComentid}")
    //RequiresPermissions("whiteholeblog:comcoment:info")
    public R info(@PathVariable("comComentid") Long comComentid){
		ComComentEntity comComent = comComentService.getById(comComentid);

        return R.ok().put("comComent", comComent);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("whiteholeblog:comcoment:save")
    public R save(@RequestBody ComComentEntity comComent){
		comComentService.save(comComent);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("whiteholeblog:comcoment:update")
    public R update(@RequestBody ComComentEntity comComent){
		comComentService.updateById(comComent);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("whiteholeblog:comcoment:delete")
    public R delete(@RequestBody Long[] comComentids){
		comComentService.removeByIds(Arrays.asList(comComentids));

        return R.ok();
    }

}
