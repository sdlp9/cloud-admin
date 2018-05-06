package com.boot.cloudadmin.api.service;

import com.baomidou.mybatisplus.service.IService;
import com.boot.cloudadmin.api.entity.WebUserEntity;
import com.boot.cloudadmin.api.form.WebLoginForm;

import java.util.Map;

public interface WebUserService extends IService<WebUserEntity>{
    /**
     * 用户登录
     * @param form    登录表单
     * @return        返回登录信息
     */
    Map<String, Object> login(WebLoginForm form);

    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    WebUserEntity queryByUserName(String userName);
}
