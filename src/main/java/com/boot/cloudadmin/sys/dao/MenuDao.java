package com.boot.cloudadmin.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.boot.cloudadmin.sys.entity.MenuEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuDao extends BaseMapper<MenuEntity>{

    /**
     * 获取用户菜单列表
     */
    List<MenuEntity> getUserMenuList(Long userId);
}
