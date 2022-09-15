package com.huterox.whitehole.whiteholeuser.entity;

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
@TableName("user_headimg")
public class HeadimgEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 这个是用户头像的一个ID，作为主键使用，这里的话就不用图床了，由服务器保存
	 */
	@TableId
	private Long imgid;
	/**
	 * 用户图片保存的地址
	 */
	private String imgpath;
	/**
	 * 这个是用户的id
	 */
	private String userid;
	/**
	 * 图片的上传时间
	 */
	private Date creatTime;

}
