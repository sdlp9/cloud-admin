package com.boot.cloudadmin.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 附件记录表
 *
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018-05-04 23:53:33
 */
@TableName("t_b_attachs")
public class AttachsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 文件名称
	 */
	private String name;
	/**
	 * 文件类型
	 */
	private String type;
	/**
	 * 状态 1、可用 2、已删除
	 */
	private Integer status;
	/**
	 * 文件大小
	 */
	private Long fileSize;
	/**
	 * 文件路径
	 */
	private String filePath;
	/**
	 * 文件后缀
	 */
	private String suffix;
	/**
	 * 关联类型 1、新闻资讯 2、项目 3、资讯富文本 4、项目富文本
	 */
	private Integer attachType;
	/**
	 * 主记录id
	 */
	private Long relId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：文件名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：文件名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：文件类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：文件类型
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：状态 1、可用 2、已删除
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态 1、可用 2、已删除
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：文件大小
	 */
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	/**
	 * 获取：文件大小
	 */
	public Long getFileSize() {
		return fileSize;
	}
	/**
	 * 设置：文件路径
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * 获取：文件路径
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * 设置：文件后缀
	 */
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	/**
	 * 获取：文件后缀
	 */
	public String getSuffix() {
		return suffix;
	}
	/**
	 * 设置：关联类型 1、新闻资讯 2、项目 3、资讯富文本 4、项目富文本
	 */
	public void setAttachType(Integer attachType) {
		this.attachType = attachType;
	}
	/**
	 * 获取：关联类型 1、新闻资讯 2、项目 3、资讯富文本 4、项目富文本
	 */
	public Integer getAttachType() {
		return attachType;
	}
	/**
	 * 设置：主记录id
	 */
	public void setRelId(Long relId) {
		this.relId = relId;
	}
	/**
	 * 获取：主记录id
	 */
	public Long getRelId() {
		return relId;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
}
