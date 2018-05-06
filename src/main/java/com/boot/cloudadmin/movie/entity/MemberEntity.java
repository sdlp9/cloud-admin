package com.boot.cloudadmin.movie.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员表
 *
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018-05-06 18:43:09
 */
@TableName("t_b_member")
public class MemberEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 密码盐值
	 */
	private String salt;
	/**
	 * 注册时间
	 */
	private Date createTime;
	/**
	 * 邀请码
	 */
	private String inviteCode;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 性别 1 男 0 女
	 */
	private Integer sex;
	/**
	 * 年龄
	 */
	private Integer age;
	/**
	 * 通讯地址
	 */
	private String address;
	/**
	 * 头像存储uuid
	 */
	private String photo;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 状态 1、可用 2、已删除
	 */
	private Integer status;
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
	 * 设置：手机号
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：手机号
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：密码盐值
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}
	/**
	 * 获取：密码盐值
	 */
	public String getSalt() {
		return salt;
	}
	/**
	 * 设置：注册时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：注册时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：邀请码
	 */
	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}
	/**
	 * 获取：邀请码
	 */
	public String getInviteCode() {
		return inviteCode;
	}
	/**
	 * 设置：姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：性别 1 男 0 女
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	/**
	 * 获取：性别 1 男 0 女
	 */
	public Integer getSex() {
		return sex;
	}
	/**
	 * 设置：年龄
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	/**
	 * 获取：年龄
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * 设置：通讯地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：通讯地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：头像存储uuid
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	/**
	 * 获取：头像存储uuid
	 */
	public String getPhoto() {
		return photo;
	}
	/**
	 * 设置：邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：邮箱
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置：用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：用户名
	 */
	public String getUserName() {
		return userName;
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
