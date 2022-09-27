package com.huterox.whiteholecould.entity.user;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@Data
@TableName("user_log_quiz")
public class LogQuizEntity implements Serializable {
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
	private Date creatTime;
	/**
	 * 1-问题发布 2-问题删除 3-问题修改
	 */
	private Integer action;
	/**
	 * 
	 */
	private String quizTitle;

}
