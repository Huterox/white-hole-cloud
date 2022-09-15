package com.huterox.whitehole.whiteholequiz.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
@Data
@TableName("quiz_quiz")
public class QuizEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户的id，用户的id是uuid生成的
	 */
	private String userid;
	/**
	 * 问题的主键，除了user其他的都是使用id自增的策略
	 */
	@TableId
	private Long quizid;
	/**
	 * 问题的具体描述
	 */
	private String quizContent;
	/**
	 * 问题的标题
	 */
	private String quizTitle;
	/**
	 * 用户的昵称，提问题者的昵称
	 */
	private String userNickname;
	/**
	 * 用户的头像，当用户更换头像的时候，这个也需要更新
	 */
	private String userImg;
	/**
	 * 问题的收藏量
	 */
	private Integer quizCollectNumber;
	/**
	 * 问题的流量量，这个浏览量是只要你点开了这个连接就算，就算没有用户登录也算。
	 */
	private Integer quizViewNumber;
	/**
	 * 问题的点赞量
	 */
	private Integer quizLikeNumber;
	/**
	 * 问题回答的数量
	 */
	private Integer quizAnsNumber;
	/**
	 * 问题的状态，正常，审核，审核失败，下架
	 */
	private Integer status;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
