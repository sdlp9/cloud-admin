package com.boot.cloudadmin.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.boot.cloudadmin.common.base.PageUtils;
import com.boot.cloudadmin.sys.entity.RoleEntity;

import java.util.Map;

/**
 * 角色
 *
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018-04-22 15:20:02
 */
public interface IRoleService extends IService<RoleEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

