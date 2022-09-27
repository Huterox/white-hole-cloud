package com.huterox.whiteholecould.entity.blog;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 收藏一定是喜欢，喜欢一定收藏，所以只要用户点击了收藏，就默认喜欢，我们对问题的受欢迎度进行筛选就是先通过收藏数，然后是喜欢数，最后是浏览量来搞的。


 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 09:21:46
 */
@Data
@TableName("blogs_like")
public class LikeEntity implements Serializable {
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
	 * 
	 */
	private String userNickname;
	/**
	 * 
	 */
	private String blogUserid;
	/**
	 * 
	 */
	private String blogUsernickname;

}
