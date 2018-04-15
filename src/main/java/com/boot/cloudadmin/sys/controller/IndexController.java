package com.boot.cloudadmin.sys.controller;

import com.boot.cloudadmin.common.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/index")
public class IndexController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/toIndex")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("test","第一个程序");
        return modelAndView;
    }

    @RequestMapping("/toHome")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView("/home/console");
        modelAndView.addObject("test","第一个程序");
        return modelAndView;
    }
}
