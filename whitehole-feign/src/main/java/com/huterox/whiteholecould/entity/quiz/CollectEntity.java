package com.huterox.whiteholecould.entity.quiz;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 这个是问题的收藏，我们来看看是谁收藏了这个问题
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
@Data
@TableName("quiz_collect")
public class CollectEntity implements Serializable {
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
	 * 收藏这个问题的用户的名字
	 */
	private String userNickname;
	/**
	 * 提问者的一个id
	 */
	private String quizUserid;
	/**
	 * 问题的提问者的昵称
	 */
	private String quizUsernickname;

}
