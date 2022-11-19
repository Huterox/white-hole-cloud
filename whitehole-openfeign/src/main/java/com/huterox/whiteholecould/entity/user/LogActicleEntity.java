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
@TableName("user_log_acticle")
public class LogActicleEntity implements Serializable {
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
	 * 1-增 2-删除 3-查 4-改   一般情况下，查我们是不会去记录的
	 */
	private Integer action;
	/**
	 * 
	 */
	private String creteTime;
	/**
	 * 
	 */
	private String articleTitle;

}
