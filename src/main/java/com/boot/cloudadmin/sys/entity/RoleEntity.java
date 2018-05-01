package com.boot.cloudadmin.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色
 *
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018-04-22 15:20:02
 */
@TableName("t_sys_role")
public class RoleEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Long roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 备注
     */
    private String remark;

    /**
     * 菜单列表
     */
    @TableField(exist=false)
    private List<Long> menuIdList;

    /**
     * 创建者ID
     */
    private Long createUserId;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 设置：
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    /**
     * 获取：
     */
    public Long getRoleId() {
        return roleId;
    }
    /**
     * 设置：角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    /**
     * 获取：角色名称
     */
    public String getRoleName() {
        return roleName;
    }
    /**
     * 设置：备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    /**
     * 获取：备注
     */
    public String getRemark() {
        return remark;
    }
    /**
     * 设置：创建者ID
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }
    /**
     * 获取：创建者ID
     */
    public Long getCreateUserId() {
        return createUserId;
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

    public List<Long> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<Long> menuIdList) {
        this.menuIdList = menuIdList;
    }
}
