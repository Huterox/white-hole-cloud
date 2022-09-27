package com.huterox.whiteholecould.entity.user;

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
 * @date 2022-09-14 08:21:09
 */
@Data
@TableName("user_log_fork")
public class LogForkEntity implements Serializable {
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
	 * 1-提交 2-删除提交
	 */
	private Integer action;

}
