package com.boot.cloudadmin.sys.controller;

import com.boot.cloudadmin.common.base.BaseController;
import com.boot.cloudadmin.common.base.PageUtils;
import com.boot.cloudadmin.common.base.R;
import com.boot.cloudadmin.sys.entity.UserEntity;
import com.boot.cloudadmin.sys.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;


    @RequestMapping("/userList")
    public ModelAndView toList(){
        return new ModelAndView("views/user/user-list");
    }

    /**
     * 获取用户列表
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public R userList(HttpServletRequest request){
        Map<String,Object> params = this.getAllParams(request);
        logger.info("获取用户列表：params:" + params.toString());
        PageUtils page = userService.queryPage(params);
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
            UserEntity userEntity = userService.selectById(Long.valueOf(params.get("id").toString()));
            modelAndView.addObject("userEntity",userEntity);
        }
        return modelAndView;
    }
}
