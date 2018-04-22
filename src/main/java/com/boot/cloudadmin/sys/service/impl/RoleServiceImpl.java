package com.boot.cloudadmin.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.boot.cloudadmin.common.base.PageUtils;
import com.boot.cloudadmin.common.base.Query;
import com.boot.cloudadmin.sys.dao.RoleDao;
import com.boot.cloudadmin.sys.entity.RoleEntity;
import com.boot.cloudadmin.sys.entity.UserEntity;
import com.boot.cloudadmin.sys.service.IRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


@Service("roleService")
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleEntity> implements IRoleService {

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

}
