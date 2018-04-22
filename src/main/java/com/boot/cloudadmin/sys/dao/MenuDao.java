package com.boot.cloudadmin.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.boot.cloudadmin.sys.entity.MenuEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuDao extends BaseMapper<MenuEntity>{

    /**
     * 获取用户按钮权限
     * @param userId
     * @return
     */
    List<String> getUserPermissions(Long userId);

    /**
     * 查询用户所有菜单id
     * @param userId
     * @return
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 根据父ID查询子菜单
     * @param parentId
     * @return
     */
    List<MenuEntity> queryListByParentId(Long parentId);
}
