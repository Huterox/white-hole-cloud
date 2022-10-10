package com.huterox.whitehole.whiteholeuser.entity.base;

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
 * @date 2022-09-14 08:21:09
 */
@Data
@TableName("user_blog_fork")
public class BlogForkEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String userid;
	/**
	 * 
	 */
	private Long articleid;
	/**
	 * 
	 */
	private Date creatTime;
	/**
	 * 
	 */
	private String articleTitle;
	/**
	 * 文章作者的id
	 */
	private String articleUserid;
	/**
	 * 文章作者的昵称
	 */
	private String articleUserNickname;

}
