package com.huterox.whiteholecould.entity.blog;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章被fork的状况
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:21:46
 */
@Data
@TableName("blogs_fork")
public class ForkEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String userid;
	/**
	 * 
	 */
	private Long blogid;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 这篇博客的作者的id
	 */
	private String blogUserid;
	/**
	 * 
	 */
	private String blogTitle;
	/**
	 * 这篇文章的作者的名字
	 */
	private String blogUserNickname;

}
