package com.huterox.whitehole.whiteholeblog.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 问题回答的评论
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:21:46
 */
@Data
@TableName("blogs_comment")
public class CommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 博客的评论者的id
	 */
	private String userid;
	/**
	 * 博客的评论者的昵称
	 */
	private String userNickname;
	/**
	 * 博客的评论者的头像
	 */
	private String userImg;
	/**
	 * 博客的评论者的评论
	 */
	private String comment;
	/**
	 * 评论的id
	 */
	@TableId
	private Long commentid;
	/**
	 * 被评论的博客的id
	 */
	private Long blogid;
	/**
	 * 评论的时间
	 */
	private Date createTime;
	/**
	 * 点赞的个数，这里的话只要是评论都只有点赞
	 */
	private Integer likeNumber;

}
