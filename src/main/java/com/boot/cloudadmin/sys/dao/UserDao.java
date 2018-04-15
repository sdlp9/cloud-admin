package com.boot.cloudadmin.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.boot.cloudadmin.sys.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao extends BaseMapper<UserEntity>{
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
