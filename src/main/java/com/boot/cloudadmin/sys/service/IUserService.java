package com.boot.cloudadmin.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.boot.cloudadmin.sys.entity.UserEntity;

import java.util.List;

public interface IUserService extends IService<UserEntity> {
    /**
     * 通过用户名获取用户
     * @param username
     * @return
     */
    UserEntity queryByUserName(String username);

    /**
     * 获取用户所有权限
     * @param userId
     * @return
     */
    List<String> queryAllPerms(Long userId);
}
