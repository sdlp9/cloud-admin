package com.boot.cloudadmin.sys.controller;

import com.boot.cloudadmin.common.annotation.SysLog;
import com.boot.cloudadmin.common.base.BaseController;
import com.boot.cloudadmin.common.base.PageUtils;
import com.boot.cloudadmin.common.base.R;
import com.boot.cloudadmin.common.validator.ValidatorUtils;
import com.boot.cloudadmin.common.validator.group.AddGroup;
import com.boot.cloudadmin.common.validator.group.UpdateGroup;
import com.boot.cloudadmin.sys.entity.UserEntity;
import com.boot.cloudadmin.sys.service.IUserRoleService;
import com.boot.cloudadmin.sys.service.IUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRoleService userRoleService;


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
    @RequiresPermissions("sys:user:list")
    public R userList(HttpServletRequest request){
        Map<String,Object> params = this.getAllParams(request);
        logger.info("获取用户列表：params:" + params.toString());
        PageUtils page = userService.queryPage(params);
        return R.ok().put("count",page.getTotalCount()).put("data",page.getList());
    }

    /**
     * 用户信息
     */
    @GetMapping("/info/{userId}")
    @RequiresPermissions("sys:user:info")
    @ResponseBody
    public R info(@PathVariable("userId") String userId){
        logger.info("获取用户信息：params：userId ---> " + userId);
        UserEntity user = userService.selectById(Long.valueOf(userId));
        //获取用户所属的角色列表
        List<Long> roleIdList = userRoleService.queryRoleIdList(Long.valueOf(userId));
        user.setRoleIdList(roleIdList);
        return R.ok().put("user", user);
    }
    /**
     * 保存用户
     */
    @SysLog("保存用户")
    @RequestMapping("/save")
    @RequiresPermissions("sys:user:save")
    @ResponseBody
    public R save(@RequestBody UserEntity user){
        logger.info("保存用户信息：params：user ---> " + user.toString());
        R r = ValidatorUtils.validateEntity(user, AddGroup.class);
        if(!r.get("code").toString().equals("0")){
            return r;
        }
        user.setCreateUserId(getUserId());
        userService.save(user);

        return R.ok();
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("sys:user:update")
    public R updateUser(@RequestBody UserEntity user){
        logger.info("更新用户信息：params：user ---> " + user.toString());
        R r = ValidatorUtils.validateEntity(user, UpdateGroup.class);
        if(!r.get("code").toString().equals("0")){
            return r;
        }
        user.setCreateUserId(getUserId());
        userService.update(user);

        return R.ok();
    }

    /**
     * 批量删除
     * @param userIds
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteByIds")
    @RequiresPermissions("sys:user:delete")
    public R deleteByIds(@RequestBody Long[] userIds){
        logger.info("批量删除用户信息：params：user ---> " + userIds.toString());
        userService.deleteBatch(userIds);
        return R.ok();
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteById")
    public R deleteById(@PathVariable("userId") String userId){
        userService.deleteById(Long.valueOf(userId));
        return R.ok();
    }
}
