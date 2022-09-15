package com.huterox.whitehole.whiteholehole.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 目前的话，我们的树洞只有两种功能，所以的话，我们这边就按照功能进行建表。
现在这个表是关于用户日志的表。
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-15 10:37:20
 */
@Data
@TableName("hole_log_log")
public class LogLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String userid;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private String content;

}
