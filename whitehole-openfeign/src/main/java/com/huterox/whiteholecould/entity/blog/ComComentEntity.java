package com.huterox.whiteholecould.entity.blog;

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
 * @date 2022-09-15 09:21:46
 */
@Data
@TableName("blogs_com_coment")
public class ComComentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private String userid;
	/**
	 * 写评论的评论的用户i
	 */
	private Long commentid;
	/**
	 * 写评论的用户的id
	 */
	private String commentUserid;
	/**
	 * 写评论的那个用户的昵称
	 */
	private String commentNickname;
	/**
	 * 写评论的评论的用户昵称
	 */
	private String userNickname;
	/**
	 * 
	 */
	private String userimg;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 评论的评论的主键
	 */
	@TableId
	private Long comComentid;
	/**
	 * 评论的评论的内容
	 */
	private String comment;
	/**
	 * 
	 */
	private Integer likeNumber;

}
