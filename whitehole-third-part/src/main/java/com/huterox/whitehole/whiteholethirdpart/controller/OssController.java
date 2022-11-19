package com.huterox.whitehole.whiteholethirdpart.controller;
import com.aliyun.oss.OSS;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.huterox.common.utils.R;
import com.huterox.whitehole.whiteholethirdpart.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 对象存储
 */
@RestController
public class OssController {
    @Autowired
    OssService ossService;

    /**
     * 这里我们就不用做那些验证了，到时候直接限流就好了
     * Oss 获取服务端签名
     * @return
     */
    @RequestMapping("/oss/policy")
    public R policy() {
        return ossService.policy();
    }
    @RequestMapping("oss/userSpaceUpImgPolicy")
    public R userSpaceUpImgPolicy() {
        return ossService.userSpaceUpImgPolicy();
    }

    @RequestMapping("oss/quizWriteAnsImgPolicy")
    public R quizWriteAnsImgPolicy() {
        return ossService.quizWriteAnsImgPolicy();
    }

    @RequestMapping("oss/blogWriteImgPolicy")
    public R blogWriteImgPolicy() {
        return ossService.blogWriteImgPolicy();
    }

    @RequestMapping("oss/blogFaceImgPolicy")
    public R blogFaceImgPolicy() {
        return ossService.blogFaceImgPolicy();
    }

    @RequestMapping("oss/communityImgPolicy")
    public R communityImgPolicy(){
        return ossService.communityImgPolicy();
    }

}
