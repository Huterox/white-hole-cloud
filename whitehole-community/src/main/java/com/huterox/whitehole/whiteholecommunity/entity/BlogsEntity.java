package com.huterox.whitehole.whiteholecommunity.entity;

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
 * @date 2022-09-15 10:34:12
 */
@Data
@TableName("community_blogs")
public class BlogsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long communityid;
	/**
	 * 
	 */
	private Long blogid;
	/**
	 * 
	 */
	private String blogTitle;
	/**
	 * 
	 */
	private String blogInfo;
	/**
	 * 
	 */
	private String blogImg;
	/**
	 * 博客对应的用户（写入到社区的博客）
	 */
	private String userId;
	/**
	 * 
	 */
	private String userNickname;
	/**
	 * 
	 */
	private String userImg;
	/**
	 * 
	 */
	private Date createTime;

}
