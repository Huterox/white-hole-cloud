package com.huterox.whiteholecould.feign.quiz;


import com.huterox.common.utils.R;
import com.huterox.whiteholecould.entity.quiz.Q.CommunityUpQuizQ;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("quiz")
@RequestMapping("/quiz/base")
public interface FeignCommunityUpQuizService {
    @PostMapping("/communityUpQuiz")
    public R communityUpQuiz(CommunityUpQuizQ communityUpQuizQ);

}
