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
@TableName("user_article")
public class ArticleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String userid;
	/**
	 * 文章的id，除了UserID之外的话，我们所有的ID使用自增ID，未来可以使用ID大小表示稀有度
	 */
	private Long articleid;
	/**
	 * 文章的标题，虽然设置为128但是，实际上只是为了安全，多做了一点。

	 */
	private String articleTitle;
	/**
	 * 文章的大致信息，这边在文章创建的时候都会一起在用户的地方保存一下，为了减少微服务之间的一个调用，所以这里做一个妥协，牺牲数据冗余，来提高系统的性能。

	 */
	private String articleInfo;
	/**
	 * 文章的封面，在用户修改的时候也是需要修改的，多查询，少修改，因此这边是可以接受的
	 */
	private String articleImg;
	/**
	 * 文章的创建时间

	 */
	private Date createTime;
	/**
	 * 文章的修改时间
	 */
	private Date updateTime;

}
