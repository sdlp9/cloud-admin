package com.boot.cloudadmin.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.boot.cloudadmin.common.base.PageUtils;
import com.boot.cloudadmin.sys.entity.UserEntity;

import java.util.List;
import java.util.Map;

public interface IUserService extends IService<UserEntity> {

    /**
     * 获取分页列表
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

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

    /**
     * 保存用户
     */
    void save(UserEntity user);

    /**
     * 修改用户
     */
    void update(UserEntity user);

    /**
     * 删除用户
     */
    void deleteBatch(Long[] userIds);

    /**
     * 根据id删除
     * @param userId
     */
    void deleteById(Long userId);
}
