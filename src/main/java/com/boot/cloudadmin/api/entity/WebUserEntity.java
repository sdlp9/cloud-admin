package com.boot.cloudadmin.api.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
@TableName("t_b_member")
public class WebUserEntity {
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
}
