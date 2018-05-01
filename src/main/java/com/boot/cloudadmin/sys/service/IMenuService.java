package com.boot.cloudadmin.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.boot.cloudadmin.sys.entity.MenuEntity;

import java.util.List;

public interface IMenuService extends IService<MenuEntity> {

    /**
     * 获取用户菜单列表
     */
    List<MenuEntity> getUserMenuList(Long userId);

    /**
     * 根据用户id 获取用户的按钮权限
     * @param userId
     * @return
     */
    List<String> getUserPermissions(Long userId);

    /**
     * 根据父ID查询子菜单
     * @param parentId
     * @return
     */
    List<MenuEntity> queryListByParentId(Long parentId);

    /**
     * 获取菜单和目录类型的菜单列表
     * @return
     */
    List<MenuEntity> queryNotButtonList();

    /**
     * 删除菜单
     * @param menuId
     */
    void delete(Long menuId);
}
