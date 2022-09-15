package com.huterox.whitehole.whiteholequiz.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 这个是回答的一个喜欢的记录
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
@Data
@TableName("quiz_ans_like")
public class AnsLikeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String userid;
	/**
	 * 
	 */
	private String ansUserid;
	/**
	 * 
	 */
	private Long ansid;
	/**
	 * 
	 */
	private String userNickname;
	/**
	 * 
	 */
	private Date creatTime;

}
