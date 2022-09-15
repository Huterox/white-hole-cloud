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
@TableName("quiz_ans_comment_like")
public class AnsCommentLikeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String userid;
	/**
	 * 点赞者的昵称，这个评论只有回复和点赞
	 */
	private String userNickname;
	/**
	 * 评论的id
	 */
	private Long commentid;
	/**
	 * 哪个问题的id，是在哪个问题下面的
	 */
	private Long ansid;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 是谁写的评论
	 */
	private String commentUserid;
	/**
	 * 写这个评论的用户昵称
	 */
	private String commentUserNickname;

}
