package com.boot.cloudadmin.sys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.boot.cloudadmin.common.annotation.SysLog;
import com.boot.cloudadmin.common.base.BaseController;
import com.boot.cloudadmin.common.base.R;
import com.boot.cloudadmin.common.contants.GlobalContants;
import com.boot.cloudadmin.sys.entity.MenuEntity;
import com.boot.cloudadmin.sys.service.IMenuService;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @RequestMapping("/menuList")
    public ModelAndView listPage(){
        logger.info("===跳转菜单列表页===");
        return new ModelAndView("views/menu/menu-list");
    }

    /**
     * 所有菜单列表
     */
    //@RequiresPermissions("sys:menu:list")
    @ResponseBody
    @RequestMapping("/list")
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

    /**
     * 选择菜单(添加、修改菜单)
     */
    @ResponseBody
    @RequestMapping("/select")
    @RequiresPermissions("sys:menu:select")
    public R select(){
        //查询列表数据
        List<MenuEntity> menuList = menuService.queryNotButtonList();

        //添加顶级菜单
        MenuEntity root = new MenuEntity();
        root.setMenuId(0L);
        root.setName("一级菜单");
        root.setParentId(-1L);
        root.setOpen(true);
        menuList.add(root);

        return R.ok().put("menuList", menuList);
    }

    /**
     * 菜单信息
     */
    @ResponseBody
    @RequestMapping("/info/{menuId}")
    @RequiresPermissions("sys:menu:info")
    public R info(@PathVariable("menuId") Long menuId){
        MenuEntity menu = menuService.selectById(menuId);
        return R.ok().put("menu", menu);
    }

    /**
     * 保存
     */
    @ResponseBody
    @SysLog("保存菜单")
    @RequestMapping("/save")
    @RequiresPermissions("sys:menu:save")
    public R save(@RequestBody MenuEntity menu){
        //数据校验
        R r = verifyForm(menu);
        if(!r.get("code").toString().equals("0")){
            return r;
        }

        menuService.insert(menu);

        return R.ok();
    }

    /**
     * 修改
     */
    @ResponseBody
    @SysLog("修改菜单")
    @RequestMapping("/update")
    @RequiresPermissions("sys:menu:update")
    public R update(@RequestBody MenuEntity menu){
        //数据校验
        R r = verifyForm(menu);
        if(!r.get("code").toString().equals("0")){
            return r;
        }
        menuService.updateById(menu);

        return R.ok();
    }

    /**
     * 删除
     */
    @ResponseBody
    @SysLog("删除菜单")
    @RequestMapping("/delete")
    @RequiresPermissions("sys:menu:delete")
    public R delete(long menuId){
        if(menuId <= 31){
            return R.error("系统菜单，不能删除");
        }

        //判断是否有子菜单或按钮
        List<MenuEntity> menuList = menuService.queryListByParentId(menuId);
        if(menuList.size() > 0){
            return R.error("请先删除子菜单或按钮");
        }

        menuService.delete(menuId);

        return R.ok();
    }

    /**
     * 验证参数是否正确
     */
    private R verifyForm(MenuEntity menu){
        if(StringUtils.isBlank(menu.getName())){
            return R.error(GlobalContants.VALIDATOR_CODE,"菜单名称不能为空");
        }

        if(menu.getParentId() == null){
            return R.error(GlobalContants.VALIDATOR_CODE,"上级菜单不能为空");
        }

        //菜单
        if(menu.getType() == GlobalContants.MenuType.MENU.getValue()){
            if(StringUtils.isBlank(menu.getUrl())){
                return R.error(GlobalContants.VALIDATOR_CODE,"菜单URL不能为空");
            }
        }

        //上级菜单类型
        int parentType = GlobalContants.MenuType.CATALOG.getValue();
        if(menu.getParentId() != 0){
            MenuEntity parentMenu = menuService.selectById(menu.getParentId());
            parentType = parentMenu.getType();
        }

        //目录、菜单
        if(menu.getType() == GlobalContants.MenuType.CATALOG.getValue() ||
                menu.getType() == GlobalContants.MenuType.MENU.getValue()){
            if(parentType != GlobalContants.MenuType.CATALOG.getValue()){
                return R.error(GlobalContants.VALIDATOR_CODE,"上级菜单只能为目录类型");
            }
        }

        //按钮
        if(menu.getType() == GlobalContants.MenuType.BUTTON.getValue()){
            if(parentType != GlobalContants.MenuType.MENU.getValue()){
                return R.error(GlobalContants.VALIDATOR_CODE,"上级菜单只能为菜单类型");
            }
        }
        return R.ok();
    }
}
