package com.huterox.whiteholecould.entity.quiz;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 问题回答的评论
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:39:35
 */
@Data
@TableName("quiz_ans_comment")
public class AnsCommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 回答的评论者的id
	 */
	private String userid;
	/**
	 * 回答的评论者的昵称
	 */
	private String userNickname;
	/**
	 * 回答的评论者的头像
	 */
	private String userImg;
	/**
	 * 回答的评论者的评论
	 */
	private String comment;
	/**
	 * 评论的id
	 */
	@TableId
	private Long commentid;
	/**
	 * 被评论的回答的id
	 */
	private Long ansid;
	/**
	 * 评论的时间
	 */
	private Date createTime;
	/**
	 * 点赞的个数，这里的话只要是评论都只有点赞
	 */
	private Integer likeNumber;

}
