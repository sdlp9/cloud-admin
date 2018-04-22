package com.boot.cloudadmin.sys.controller;

import com.boot.cloudadmin.common.base.BaseController;
import com.boot.cloudadmin.common.base.PageUtils;
import com.boot.cloudadmin.common.base.R;
import com.boot.cloudadmin.sys.entity.RoleEntity;
import com.boot.cloudadmin.sys.service.IRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * 角色
 *
 * @author liuyuzhu
 * @email liuyuzhu.1314@gmail.com
 * @date 2018-04-22 15:20:02
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/roleList")
    public ModelAndView toList(){
        return new ModelAndView("views/role/role-list");
    }

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/list")
    //@RequiresPermissions("project:role:list")
    public R list(HttpServletRequest request){
        Map<String,Object> params = this.getAllParams(request);
        logger.info("获取角色列表：params:" + params.toString());
        PageUtils page = roleService.queryPage(params);
        return R.ok().put("count",page.getTotalCount()).put("data",page.getList());
    }

    /**
     * 跳转增加页面
     * @return
     */
    @RequestMapping("/goAdd")
    public ModelAndView goAdd(HttpServletRequest request){
        Map<String,Object> params = this.getAllParams(request);
        logger.info("跳转增加页面：params --> " + params.toString());
        ModelAndView modelAndView = new ModelAndView("views/user/user-edit");
        if(params.containsKey("id")){
            RoleEntity roleEntity = roleService.selectById(Long.valueOf(params.get("id").toString()));
            modelAndView.addObject("roleEntity",roleEntity);
        }
        return modelAndView;
    }
}
