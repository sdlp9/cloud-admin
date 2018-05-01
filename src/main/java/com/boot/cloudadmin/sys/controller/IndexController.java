package com.boot.cloudadmin.sys.controller;

import com.boot.cloudadmin.common.base.BaseController;
import com.boot.cloudadmin.common.base.R;
import com.boot.cloudadmin.common.utils.date.DateUtil;
import com.boot.cloudadmin.common.utils.shiro.ShiroUtils;
import com.boot.cloudadmin.sys.entity.MenuEntity;
import com.boot.cloudadmin.sys.entity.UserEntity;
import com.boot.cloudadmin.sys.service.IMenuService;
import com.sun.management.OperatingSystemMXBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        ModelAndView modelAndView = new ModelAndView("console");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("index/info")
    public R info(){
        OperatingSystemMXBean osmx = (OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();

        Map<String, Object> map = new HashMap<>();
        map.put("sysTime", DateUtil.getDateStr(new Date(), "yyyy年MM月dd日 HH:mm:ss"));
        map.put("osName", System.getProperty("os.name"));
        map.put("osArch", System.getProperty("os.arch"));
        map.put("osVersion", System.getProperty("os.version"));
        map.put("userLanguage", System.getProperty("user.language"));
        map.put("userDir", System.getProperty("user.dir"));
        map.put("totalPhysical", osmx.getTotalPhysicalMemorySize()/1024/1024);
        map.put("freePhysical", osmx.getFreePhysicalMemorySize()/1024/1024);
        map.put("memoryRate", BigDecimal.valueOf((1-osmx.getFreePhysicalMemorySize()*1.0/osmx.getTotalPhysicalMemorySize())*100).setScale(2, RoundingMode.HALF_UP));
        map.put("processors", osmx.getAvailableProcessors());
        map.put("jvmName", System.getProperty("java.vm.name"));
        map.put("javaVersion", System.getProperty("java.version"));
        map.put("javaHome", System.getProperty("java.home"));
        map.put("javaTotalMemory", Runtime.getRuntime().totalMemory()/1024/1024);
        map.put("javaFreeMemory", Runtime.getRuntime().freeMemory()/1024/1024);
        map.put("javaMaxMemory", Runtime.getRuntime().maxMemory()/1024/1024);
        map.put("userName", System.getProperty("user.name"));
        map.put("systemCpuLoad", BigDecimal.valueOf(osmx.getSystemCpuLoad()*100).setScale(2, RoundingMode.HALF_UP));
        map.put("userTimezone", System.getProperty("user.timezone"));

        return R.ok().put("info", map);
    }

}
