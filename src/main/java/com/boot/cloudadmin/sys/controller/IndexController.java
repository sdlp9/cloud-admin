package com.boot.cloudadmin.sys.controller;

import com.boot.cloudadmin.common.base.BaseController;
import com.boot.cloudadmin.common.utils.shiro.ShiroUtils;
import com.boot.cloudadmin.sys.entity.MenuEntity;
import com.boot.cloudadmin.sys.entity.UserEntity;
import com.boot.cloudadmin.sys.service.IMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private IMenuService menuService;

    @RequestMapping(value = {"/","/toIndex"})
    public ModelAndView index(){
        UserEntity user = ShiroUtils.getUserEntity();
        /** 获取登录用户的菜单 和 按钮权限**/
        List<MenuEntity> menuList = menuService.getUserMenuList(user.getUserId());
        logger.info("菜单列表：====" + menuList.toString());
        //List<String> permissions = menuService.getUserPermissions(user.getUserId());

        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("user",user);
        modelAndView.addObject("menuList",menuList);
        //modelAndView.addObject("permissions",permissions);
        return modelAndView;
    }

    @RequestMapping("/toHome")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView("/home/console");
        modelAndView.addObject("test","第一个程序");
        return modelAndView;
    }
}
