package com.huterox.whitehole.whiteholeblog.entity;

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
 * @date 2022-09-15 09:21:46
 */
@Data
@TableName("blogs_comment_like")
public class CommentLikeEntity implements Serializable {
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
	 * 哪个博客的id，是在哪个博客下面的
	 */
	private Long blogid;
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
