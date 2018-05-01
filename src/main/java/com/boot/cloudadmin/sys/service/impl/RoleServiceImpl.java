package com.boot.cloudadmin.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.boot.cloudadmin.common.base.PageUtils;
import com.boot.cloudadmin.common.base.Query;
import com.boot.cloudadmin.sys.dao.RoleDao;
import com.boot.cloudadmin.sys.entity.RoleEntity;
import com.boot.cloudadmin.sys.entity.UserEntity;
import com.boot.cloudadmin.sys.service.IRoleMenuService;
import com.boot.cloudadmin.sys.service.IRoleService;
import com.boot.cloudadmin.sys.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Map;


@Service("roleService")
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleEntity> implements IRoleService {

    @Autowired
    private IRoleMenuService roleMenuService;

    @Autowired
    private IUserRoleService userRoleService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        EntityWrapper<RoleEntity> wrapper = new EntityWrapper<RoleEntity>();
        if(params.containsKey("username")){
            wrapper.like("username",params.get("username").toString());
        }
        Page<RoleEntity> page = this.selectPage(
                new Query<RoleEntity>(params).getPage(),wrapper);

        return new PageUtils(page);
    }

    @Override
    public void save(RoleEntity roleEntity) {
        this.baseMapper.insert(roleEntity);
        //保存角色与菜单关系
        roleMenuService.saveOrUpdate(roleEntity.getRoleId(), roleEntity.getMenuIdList());
    }

    @Override
    public void update(RoleEntity roleEntity) {
        this.baseMapper.updateAllColumnById(roleEntity);
        roleMenuService.saveOrUpdate(roleEntity.getRoleId(),roleEntity.getMenuIdList());
    }

    @Override
    public void deleteBatch(Long[] roleIds) {
        //删除角色
        this.deleteBatchIds(Arrays.asList(roleIds));

        //删除角色与菜单关联
        roleMenuService.deleteBatch(roleIds);

        //删除角色与用户关联
        userRoleService.deleteBatch(roleIds);
    }

}
