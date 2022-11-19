package com.huterox.whiteholecould.entity.quiz.Q;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommunityUpQuizQ {
    //这个是远程调用使用的玩意
    private String userid;
    private String quizTitle;
    private String quizContent;
    private Long communityid;
    /**
     * 这两个是我们额外需要的玩意
     * */
    private String communityUserid;
    private String communityName;
}
