package com.boot.cloudadmin.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.boot.cloudadmin.sys.entity.MenuEntity;

import java.util.List;

public interface IMenuService extends IService<MenuEntity> {

    /**
     * 获取用户菜单列表
     */
    List<MenuEntity> getUserMenuList(Long userId);
}
