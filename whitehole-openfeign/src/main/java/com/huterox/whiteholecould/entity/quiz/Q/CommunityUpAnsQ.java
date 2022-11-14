package com.huterox.whiteholecould.entity.quiz.Q;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommunityUpAnsQ {
    private String userid;
    private Long quizid;
    private String quizTitle;
    private String context;
    private Long communityid;
}
