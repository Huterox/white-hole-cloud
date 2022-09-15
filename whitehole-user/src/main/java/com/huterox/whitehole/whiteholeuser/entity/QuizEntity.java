package com.huterox.whitehole.whiteholeuser.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 这个表是中间表，不存在主键。
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@Data
@TableName("user_quiz")
public class QuizEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String userid;
	/**
	 * 
	 */
	private Long quizid;
	/**
	 * 
	 */
	private String quizTitle;
	/**
	 * 
	 */
	private String quizInfo;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date updataTime;

}
