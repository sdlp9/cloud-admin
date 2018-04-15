package com.boot.cloudadmin.sys.controller;

import com.boot.cloudadmin.common.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/userList")
    public ModelAndView toList(){
        return new ModelAndView("component/button/index");
    }
}
