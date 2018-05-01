package com.boot.cloudadmin.sys.controller;

import com.boot.cloudadmin.common.annotation.SysLog;
import com.boot.cloudadmin.common.base.BaseController;
import com.boot.cloudadmin.common.base.PageUtils;
import com.boot.cloudadmin.common.base.R;
import com.boot.cloudadmin.common.validator.ValidatorUtils;
import com.boot.cloudadmin.common.validator.group.AddGroup;
import com.boot.cloudadmin.common.validator.group.UpdateGroup;
import com.boot.cloudadmin.sys.entity.RoleEntity;
import com.boot.cloudadmin.sys.service.IRoleMenuService;
import com.boot.cloudadmin.sys.service.IRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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

    @Autowired
    private IRoleMenuService roleMenuService;

    @RequestMapping("/roleList")
    public ModelAndView toList(){
        return new ModelAndView("views/role/role-list");
    }

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/list")
    @RequiresPermissions("sys:role:list")
    public R list(HttpServletRequest request){
        Map<String,Object> params = this.getAllParams(request);
        logger.info("获取角色列表：params:" + params.toString());
        PageUtils page = roleService.queryPage(params);
        return R.ok().put("count",page.getTotalCount()).put("data",page.getList());
    }

    /**
     * 创建用户是选择角色列表
     */
    @ResponseBody
    @RequestMapping("/select")
    @RequiresPermissions("sys:role:select")
    public R select(){
        List<RoleEntity> list = roleService.selectList(null);
        return R.ok().put("list", list);
    }

    /**
     * 保存用户
     */
    @SysLog("保存角色")
    @RequestMapping("/save")
    @RequiresPermissions("sys:role:save")
    @ResponseBody
    public R save(@RequestBody RoleEntity role){
        logger.info("保存角色信息：params：role ---> " + role.toString());
        R r = ValidatorUtils.validateEntity(role, AddGroup.class);
        if(!r.get("code").toString().equals("0")){
            return r;
        }
        role.setCreateUserId(getUserId());
        roleService.save(role);

        return R.ok();
    }
    /**
     * 角色信息
     */
    @ResponseBody
    @RequestMapping("/info/{roleId}")
    @RequiresPermissions("sys:role:info")
    public R info(@PathVariable("roleId") Long roleId){
        logger.info("获取角色信息：params：roleId ---> " + roleId);
        RoleEntity role = roleService.selectById(roleId);

        //查询角色对应的菜单
        List<Long> menuIdList = roleMenuService.queryMenuIdList(roleId);
        role.setMenuIdList(menuIdList);

        return R.ok().put("role", role);
    }

    /**
     * 修改角色
     */
    @SysLog("修改角色")
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("sys:role:update")
    public R update(@RequestBody RoleEntity role){
        logger.info("更新角色信息：params：role ---> " + role.toString());
        R r = ValidatorUtils.validateEntity(role, UpdateGroup.class);
        if(!r.get("code").toString().equals("0")){
            return r;
        }
        roleService.update(role);

        return R.ok();
    }

    /**
     * 删除角色
     */
    @SysLog("删除角色")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:role:delete")
    public R delete(@RequestBody Long[] roleIds){
        roleService.deleteBatch(roleIds);

        return R.ok();
    }
}
