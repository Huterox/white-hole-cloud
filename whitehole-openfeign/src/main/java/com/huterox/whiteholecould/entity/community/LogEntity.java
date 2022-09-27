package com.huterox.whiteholecould.entity.community;

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
 * @date 2022-09-15 10:34:12
 */
@Data
@TableName("community_log")
public class LogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long communityid;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 1-创建 2-删除 3-下架
	 */
	private Integer action;
	/**
	 * 哪个用户的
	 */
	private String userid;
	/**
	 * 
	 */
	private String userNickname;

}
