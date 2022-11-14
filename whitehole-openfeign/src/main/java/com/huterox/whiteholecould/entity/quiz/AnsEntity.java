package com.huterox.whiteholecould.entity.quiz;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

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
	private String creatTime;
	/**
	 * 回答的主要内容
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
	@TableId(type = IdType.AUTO)
	private Long ansid;
	//问题回答的状态
	private Integer status;
	//问题的标题
	@TableField("quizTitle")
	private String quizTitle;

	private Long communityid;

}
