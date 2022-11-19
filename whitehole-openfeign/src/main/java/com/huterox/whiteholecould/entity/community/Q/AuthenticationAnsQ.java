package com.huterox.whiteholecould.entity.community.Q;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationAnsQ {
    /**
     * 在进行社区的一个回答的上传的时候，需要进行一个认证处理
     * 此类为服务认证的请求类
     * */
    private String userid;
    private Long quizid;
    private Long communityid;
}
