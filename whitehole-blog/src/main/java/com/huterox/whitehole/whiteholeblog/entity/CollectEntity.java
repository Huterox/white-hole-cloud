package com.huterox.whitehole.whiteholeblog.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 这个是问题的收藏，我们来看看是谁收藏了这个问题
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:21:46
 */
@Data
@TableName("blogs_collect")
public class CollectEntity implements Serializable {
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
	private Date creatTime;
	/**
	 * 收藏这个博客的用户的名字
	 */
	private String userNickname;
	/**
	 * 提问者的一个id
	 */
	private String blogUserid;
	/**
	 * 博客的作者的昵称
	 */
	private String blogUsernickname;

}
