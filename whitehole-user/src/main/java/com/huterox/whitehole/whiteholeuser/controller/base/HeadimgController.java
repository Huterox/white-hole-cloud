package com.huterox.whitehole.whiteholeuser.controller.base;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.huterox.whiteholecould.entity.user.HeadimgEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.huterox.whitehole.whiteholeuser.service.base.HeadimgService;
import com.huterox.common.utils.PageUtils;
import com.huterox.common.utils.R;



/**
 * 
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@RestController
@RequestMapping("user/headimg")
public class HeadimgController {
    @Autowired
    private HeadimgService headimgService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //RequiresPermissions("user:headimg:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = headimgService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{imgid}")
    //RequiresPermissions("user:headimg:info")
    public R info(@PathVariable("imgid") Long imgid){
		HeadimgEntity headimg = headimgService.getById(imgid);

        return R.ok().put("headimg", headimg);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //RequiresPermissions("user:headimg:save")
    public R save(@RequestBody HeadimgEntity headimg){
		headimgService.save(headimg);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //RequiresPermissions("user:headimg:update")
    public R update(@RequestBody HeadimgEntity headimg){
		headimgService.updateById(headimg);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //RequiresPermissions("user:headimg:delete")
    public R delete(@RequestBody Long[] imgids){
		headimgService.removeByIds(Arrays.asList(imgids));

        return R.ok();
    }

}
