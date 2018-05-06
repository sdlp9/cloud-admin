package com.boot.cloudadmin.api.controller;


import com.boot.cloudadmin.api.annotation.Login;
import com.boot.cloudadmin.api.entity.WebUserEntity;
import com.boot.cloudadmin.api.form.RegisterForm;
import com.boot.cloudadmin.api.form.WebLoginForm;
import com.boot.cloudadmin.api.service.TokenService;
import com.boot.cloudadmin.api.service.WebUserService;
import com.boot.cloudadmin.common.base.R;
import com.boot.cloudadmin.common.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.Map;

/**
 * web登录接口
 */
@RestController
@RequestMapping("/api")
@Api(tags="登录接口")
public class ApiLoginController {
    @Autowired
    private WebUserService webUserService;
    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    @ApiOperation("登录")
    public R login(@RequestBody WebLoginForm form){
        //表单校验
        R r = ValidatorUtils.validateEntity(form);
        if(!r.get("code").equals("0")){
            return r;
        }
        //用户登录
        Map<String, Object> map = webUserService.login(form);

        return R.ok(map);
    }

    @Login
    @PostMapping("/logout")
    @ApiOperation("退出")
    public R logout(@ApiIgnore @RequestAttribute("userId") long userId){
        tokenService.expireToken(userId);
        return R.ok();
    }

    @PostMapping("register")
    @ApiOperation("注册")
    public R register(@RequestBody RegisterForm form){
        //表单校验
        R r = ValidatorUtils.validateEntity(form);
        if(!r.get("code").equals("0")){
            return r;
        }

        WebUserEntity user = new WebUserEntity();
        user.setUserName(form.getUserName());
        user.setPhone(form.getPhone());
        user.setEmail(form.getEmail());
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setPassword(new Sha256Hash(form.getPassword(), salt).toHex());
        user.setSalt(salt);
        user.setCreateTime(new Date());
        webUserService.insert(user);

        return R.ok();
    }
}
