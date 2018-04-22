package com.boot.cloudadmin.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.boot.cloudadmin.common.contants.GlobalContants;
import com.boot.cloudadmin.sys.dao.MenuDao;
import com.boot.cloudadmin.sys.entity.MenuEntity;
import com.boot.cloudadmin.sys.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuDao,MenuEntity> implements IMenuService{

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<MenuEntity> getUserMenuList(Long userId) {
        /**
         * 系统管理员 有最高权限
         */
        if(GlobalContants.SUPER_ADMIN == userId){
            return this.getAllMenuList(null);
        }
        //查询用户下所有菜单idlist
        List<Long> menuIdList = menuDao.queryAllMenuId(userId);
        //获取菜单
        return getAllMenuList(menuIdList);
    }

    @Override
    public List<String> getUserPermissions(Long userId) {
        return menuDao.getUserPermissions(userId);
    }

    @Override
    public List<MenuEntity> queryListByParentId(Long parentId) {
        return baseMapper.queryListByParentId(parentId);
    }

    /**
     * 根据目录id 查询目录下菜单
     * @param parentId
     * @param menuIdList
     * @return
     */
    public List<MenuEntity> queryListParentId(Long parentId, List<Long> menuIdList) {
        /** 根据父id查询 子菜单 **/
        List<MenuEntity> menuList = queryListByParentId(parentId);
        if(menuIdList == null){
            return menuList;
        }

        /** 返回 在用户菜单列表内的菜单 **/
        List<MenuEntity> userMenuList = new ArrayList<>();
        for(MenuEntity menu : menuList){
            if(menuIdList.contains(menu.getMenuId())){
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    /**
     * 获取所有菜单列表
     */
    private List<MenuEntity> getAllMenuList(List<Long> menuIdList){
        //根据目录 查询用户菜单列表内的 菜单
        List<MenuEntity> menuList = queryListParentId(0L, menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }

    /**
     * 递归
     */
    private List<MenuEntity> getMenuTreeList(List<MenuEntity> menuList, List<Long> menuIdList){
        List<MenuEntity> subMenuList = new ArrayList<MenuEntity>();

        for(MenuEntity entity : menuList){
            //目录
            if(entity.getType() == GlobalContants.MenuType.CATALOG.getValue()){
                entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }

        return subMenuList;
    }

}
