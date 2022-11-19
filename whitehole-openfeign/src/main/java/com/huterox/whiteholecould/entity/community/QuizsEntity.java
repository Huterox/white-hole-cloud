package com.huterox.whiteholecould.entity.community;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 *
 *
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:34:12
 */
@Data
@TableName("community_quizs")
public class QuizsEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    private Long communityid;

    private Long quizid;

    private String quizTitle;

    private String userid;

    private String createTime;

    private String userNickname;

    private Integer status;


}
