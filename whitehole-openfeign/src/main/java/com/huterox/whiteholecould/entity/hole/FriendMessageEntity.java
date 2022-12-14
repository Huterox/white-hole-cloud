package com.huterox.whiteholecould.entity.hole;

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
 * @date 2022-09-15 10:37:20
 */
@Data
@TableName("hole_friend_message")
public class FriendMessageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String fromid;
	/**
	 * 
	 */
	private String toid;
	/**
	 * 
	 */
	private String content;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Integer status;
	/**
	 * 
	 */
	private Integer type;

}
