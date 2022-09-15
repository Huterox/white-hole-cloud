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
@TableName("blogs_pull")
public class PullEntity implements Serializable {
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
	 * pull的用户名称
	 */
	private String userNickname;
	/**
	 * 博客作者的id
	 */
	private Long blogUserid;
	/**
	 * 博客作者的名字
	 */
	private String blogUserNickname;
	/**
	 * 提交者的用户头像
	 */
	private String userimg;
	/**
	 * 
	 */
	private Long contentid;

}
