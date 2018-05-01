package com.boot.cloudadmin.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.boot.cloudadmin.sys.dao.UserRoleDao;
import com.boot.cloudadmin.sys.entity.UserRoleEntity;
import com.boot.cloudadmin.sys.service.IUserRoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao,UserRoleEntity> implements IUserRoleService {

    @Override
    public void saveOrUpdate(Long userId, List<Long> roleIdList) {
        //先删除用户与角色关系
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("user_id",userId);
        this.deleteByMap(params);

        if(roleIdList == null || roleIdList.size() == 0){
            return ;
        }

        //保存用户与角色关系
        List<UserRoleEntity> list = new ArrayList<>(roleIdList.size());
        for(Long roleId : roleIdList){
            UserRoleEntity sysUserRoleEntity = new UserRoleEntity();
            sysUserRoleEntity.setUserId(userId);
            sysUserRoleEntity.setRoleId(roleId);

            list.add(sysUserRoleEntity);
        }
        this.insertBatch(list);
    }

    @Override
    public List<Long> queryRoleIdList(Long userId) {
        return baseMapper.queryRoleIdList(userId);
    }

    @Override
    public int deleteBatch(Long[] roleIds) {
        return baseMapper.deleteBatch(roleIds);
    }
}
