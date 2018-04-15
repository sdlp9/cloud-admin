package com.boot.cloudadmin.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.boot.cloudadmin.sys.dao.MenuDao;
import com.boot.cloudadmin.sys.entity.MenuEntity;
import com.boot.cloudadmin.sys.service.IMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuDao,MenuEntity> implements IMenuService{
}
