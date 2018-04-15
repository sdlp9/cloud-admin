package com.boot.cloudadmin.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.boot.cloudadmin.sys.dao.UserDao;
import com.boot.cloudadmin.sys.entity.UserEntity;
import com.boot.cloudadmin.sys.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao,UserEntity> implements IUserService {

    /**
     * 通过用户名获取用户
     * @param username
     * @return
     */
    @Override
    public UserEntity queryByUserName(String username) {
        return baseMapper.queryByUserName(username);
    }

    /**
     * 获取用户的所有权限
     * @param userId
     * @return
     */
    @Override
    public List<String> queryAllPerms(Long userId) {
        return baseMapper.queryAllPerms(userId);
    }
}
