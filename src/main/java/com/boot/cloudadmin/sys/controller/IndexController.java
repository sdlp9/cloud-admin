package com.boot.cloudadmin.sys.controller;

import com.boot.cloudadmin.common.base.BaseController;
import com.boot.cloudadmin.common.utils.shiro.ShiroUtils;
import com.boot.cloudadmin.sys.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = {"/","/toIndex"})
    public ModelAndView index(){
        UserEntity user = ShiroUtils.getUserEntity();
        /** 获取登录用户的菜单 **/
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("user",user);
        return modelAndView;
    }

    @RequestMapping("/toHome")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView("/home/console");
        modelAndView.addObject("test","第一个程序");
        return modelAndView;
    }
}
