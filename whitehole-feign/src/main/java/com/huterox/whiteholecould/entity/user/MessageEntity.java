package com.huterox.whiteholecould.entity.user;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 这个表是用来记录用户消息的,关于消息的具体的一些内容，或者是详情页面
是由负责该消息，或者说产生这个消息的服务去处理，显示完整的消息。
 * 
 * @author Huterox
 * @email 3139541502@qq.com
 * @date 2022-09-14 08:21:09
 */
@Data
@TableName("user_message")
public class MessageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String userid;
	/**
	 * 消息的类型
	 */
	private Integer type;
	/**
	 * 消息的id
	 */
	private Long messageid;
	/**
	 * 
	 */
	private String messageTitle;
	/**
	 * 
	 */
	private String messageInfo;
	/**
	 * 
	 */
	private String from;
	/**
	 * 
	 */
	private String to;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 源于那个服务产生的，也就是咱们具体的一服务的地址
	 */
	private String source;

}
