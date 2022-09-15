package com.huterox.whitehole.whiteholequiz.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 问题的回答
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
@Data
@TableName("quiz_ans")
public class AnsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
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
	 * 
	 */
	private String context;
	/**
	 * 
	 */
	private Integer ansViewNumber;
	/**
	 * 
	 */
	private Integer ansLikeNumber;
	/**
	 * 只要点击进入查看的回答的具体的页面，那么就+1
	 */
	private Integer ansCollectNumber;
	/**
	 * 问题回答者的昵称
	 */
	private String userNickname;
	/**
	 * 这个是问题的回答者的头像
	 */
	private String userImg;
	/**
	 * 
	 */
	@TableId
	private Long ansid;

}
