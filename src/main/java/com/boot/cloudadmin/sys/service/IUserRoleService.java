package com.boot.cloudadmin.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.boot.cloudadmin.sys.entity.UserRoleEntity;

import java.util.List;

public interface IUserRoleService extends IService<UserRoleEntity>{

    void saveOrUpdate(Long userId, List<Long> roleIdList);

    /**
     * 根据用户ID，获取角色ID列表
     */
    List<Long> queryRoleIdList(Long userId);

    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);
}
