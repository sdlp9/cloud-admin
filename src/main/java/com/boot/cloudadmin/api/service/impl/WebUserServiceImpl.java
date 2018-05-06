package com.boot.cloudadmin.api.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.boot.cloudadmin.api.dao.WebUserDao;
import com.boot.cloudadmin.api.entity.TokenEntity;
import com.boot.cloudadmin.api.entity.WebUserEntity;
import com.boot.cloudadmin.api.form.WebLoginForm;
import com.boot.cloudadmin.api.service.TokenService;
import com.boot.cloudadmin.api.service.WebUserService;
import com.boot.cloudadmin.common.exception.GlobalException;
import com.boot.cloudadmin.common.validator.Assert;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("webUserService")
public class WebUserServiceImpl extends ServiceImpl<WebUserDao,WebUserEntity> implements WebUserService {

    @Autowired
    private TokenService tokenService;

    @Override
    public Map<String, Object> login(WebLoginForm form) {
        WebUserEntity user = queryByUserName(form.getUserName());
        Assert.isNull(user, "用户名或密码错误");

        //密码错误
        if(!user.getPassword().equals(DigestUtils.sha256Hex(form.getPassword()))){
            throw new GlobalException("用户名或密码错误");
        }

        //获取登录token
        TokenEntity tokenEntity = tokenService.createToken(user.getId());

        Map<String, Object> map = new HashMap<>(2);
        map.put("token", tokenEntity.getToken());
        map.put("expire", tokenEntity.getExpireTime().getTime() - System.currentTimeMillis());

        return map;
    }

    @Override
    public WebUserEntity queryByUserName(String userName) {
        WebUserEntity webUserEntity = new WebUserEntity();
        webUserEntity.setUserName(userName);
        return baseMapper.selectOne(webUserEntity);
    }
}
