package com.huterox.whiteholecould.entity.user;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 这个是用户的回答。



 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@Data
@TableName("user_ans")
public class AnsEntity implements Serializable {
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
	 * 问题回答自动节截选的内容，通过正则表达式去截取markdown当中的一些内容
	 */
	private String ansInfo;
	/**
	 * 问题回答的id
	 */
	private Long ansid;
	/**
	 * 
	 */
	private Date creatTime;

}
