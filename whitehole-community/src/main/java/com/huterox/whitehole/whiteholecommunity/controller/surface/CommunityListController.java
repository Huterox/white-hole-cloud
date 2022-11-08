package com.huterox.whitehole.whiteholecommunity.controller.surface;


import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholecommunity.entity.surface.communityList.Q.GetunityListQ;
import com.huterox.whitehole.whiteholecommunity.service.surface.CommunityListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/community/list")
public class CommunityListController {

    @Autowired
    CommunityListService communityListService;
    @Cacheable(value={"communityListController:unityList"},key = "#entity.page")
    @RequestMapping("/unity")
    public R unityList(@Validated GetunityListQ entity) throws Exception {
        return communityListService.unityList(entity);
    }

    @RequestMapping("/unityBlogList")
    @Cacheable(value={"communityListController:unityBlogList"},
            key = "#p0.page+\":\"+#p0.communityid"
    )
    public R unityBlogList(@Validated GetunityListQ entity) throws Exception {
        return communityListService.unityBlogList(entity);
    }
    @RequestMapping("/unityQuiList")
    @Cacheable(value={"communityListController:unityQuiList"},
            key = "#p0.page+\":\"+#p0.communityid"
    )
    public R unityQuiList(@Validated GetunityListQ enitiy) throws Exception {
        return communityListService.unityQuiList(enitiy);
    }

    public R unityUserList(@Validated GetunityListQ entity){return null;}

}
