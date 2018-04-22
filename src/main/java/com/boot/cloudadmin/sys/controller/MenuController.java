package com.boot.cloudadmin.sys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.boot.cloudadmin.common.base.BaseController;
import com.boot.cloudadmin.sys.entity.MenuEntity;
import com.boot.cloudadmin.sys.service.IMenuService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController{

    private static final Logger logger = LogManager.getLogger(MenuController.class);

    @Autowired
    private IMenuService menuService;

    /**
     * 菜单列表页跳转
     * @return
     */
    @RequestMapping("/listPage")
    public ModelAndView listPage(){
        logger.info("===跳转菜单列表页===");
        return new ModelAndView("views/menu/menu-list");
    }

    /**
     * 所有菜单列表
     */
    @GetMapping("/list")
    //@RequiresPermissions("sys:menu:list")
    public List<MenuEntity> list(){
        List<MenuEntity> menuList = menuService.selectList(new EntityWrapper<MenuEntity>().eq("status",1));
        for(MenuEntity menuEntity : menuList){
            MenuEntity parentMenuEntity = menuService.selectById(menuEntity.getParentId());
            if(parentMenuEntity != null){
                menuEntity.setParentName(parentMenuEntity.getName());
            }
        }

        return menuList;
    }

}
