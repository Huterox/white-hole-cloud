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
@TableName("user_log_ans")
public class LogAnsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String userid;
	/**
	 * 
	 */
	private Long ansid;
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
	private String creatTime;
	/**
	 * 1-回答 2-回答删除 3-回答修改
	 */
	private Integer action;

}
