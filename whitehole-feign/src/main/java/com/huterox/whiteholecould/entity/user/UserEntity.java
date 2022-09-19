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
@TableName("user_user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 这个是用户名，相当于私钥
	 */
	private String username;
	/**
	 * 这个是密码，后面会使用md5加密
	 */
	private String password;
	/**
	 * 这个是用户id,使用uuid生成，是全局唯一的，相当于公钥
	 */
	@TableId
	private String userid;
	/**
	 * 用户昵称，可重复，默认会把username作为昵称，可修改
	 */
	private String nickname;
	/**
	 * 用户年龄
	 */
	private Integer age;
	/**
	 * 用户电话号码，用于后期密码找回
	 */
	private String phone;
	/**
	 * 用户邮箱，用于用户注册验证，密码找回，如果用户在本平台注册的话，是必须要的

	 */
	private String email;
	/**
	 * 用户对自己的简介描述，相当于个性签名
	 */
	private String info;
	/**
	 * 用户等级，1-普通用户 2-捐赠，高级用户
	 */
	private Integer level;
	/**
	 * 白洞值
	 */
	private Integer holeValue;
	/**
	 * 用户地址
	 */
	private String home;
	/**
	 * 用户状态，1-正常 2-警告 3-封禁
	 */
	private Integer status;
	/**
	 * 如果用户选择第三方登录，那么会将第三方的token保存到数据当中，然后自动创建userid,其余信息需要用户自己填写
	 */
	private String tokenThere;
	/**
	 * 用户加入社区的时间，格式为2022-9-12
	 */
	private Date creatTime;

}
