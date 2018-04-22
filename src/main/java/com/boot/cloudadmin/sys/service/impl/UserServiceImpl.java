package com.boot.cloudadmin.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.boot.cloudadmin.common.base.PageUtils;
import com.boot.cloudadmin.common.base.Query;
import com.boot.cloudadmin.sys.dao.UserDao;
import com.boot.cloudadmin.sys.entity.UserEntity;
import com.boot.cloudadmin.sys.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao,UserEntity> implements IUserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        EntityWrapper<UserEntity> wrapper = new EntityWrapper<UserEntity>();
        if(params.containsKey("username")){
            wrapper.like("username",params.get("username").toString());
        }
        Page<UserEntity> page = this.selectPage(
                new Query<UserEntity>(params).getPage(),wrapper);
        return new PageUtils(page);
    }

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
