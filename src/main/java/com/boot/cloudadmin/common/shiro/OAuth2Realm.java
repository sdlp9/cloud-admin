package com.boot.cloudadmin.common.shiro;

import com.boot.cloudadmin.common.contants.GlobalContants;
import com.boot.cloudadmin.common.exception.GlobalException;
import com.boot.cloudadmin.common.utils.crypt.CryptTool;
import com.boot.cloudadmin.common.utils.crypt.MD5Util;
import com.boot.cloudadmin.common.utils.shiro.ShiroUtils;
import com.boot.cloudadmin.sys.entity.MenuEntity;
import com.boot.cloudadmin.sys.entity.UserEntity;
import com.boot.cloudadmin.sys.service.IMenuService;
import com.boot.cloudadmin.sys.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * 认证
 */
@Component
public class OAuth2Realm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(OAuth2Realm.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IMenuService menuService;

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        logger.info("====================2、鉴权执行 ==========================");

        UserEntity user = (UserEntity)principals.getPrimaryPrincipal();
        Long userId = user.getUserId();
        List<String> permsList;

        /** 管理员 有最高权限**/
        if(userId == GlobalContants.SUPER_ADMIN){
            List<MenuEntity> menuList = menuService.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            for(MenuEntity menu : menuList){
                permsList.add(menu.getPerms());
            }
        }else{
            //用户权限列表
            permsList = userService.queryAllPerms(userId);
        }

        Set<String> permsSet = new HashSet<>();
        for(String perms : permsList){
            if(StringUtils.isBlank(perms)){
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("====================1、登录认证执行==========================");
        //获取用户的输入的账号.
        String userName = (String) token.getPrincipal();

        //查询用户信息
        UserEntity user = userService.queryByUserName(userName);

        logger.info("=====================获取用户信息" + user + "==============");

        if(user == null){
            throw new GlobalException("账号或密码不正确");
        }

        //账号锁定
        if(user.getStatus() == 0){
            throw new GlobalException("账号已被锁定,请联系管理员");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
        return info;
    }
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        shaCredentialsMatcher.setHashAlgorithmName(ShiroUtils.hashAlgorithmName);
        shaCredentialsMatcher.setHashIterations(ShiroUtils.hashIterations);
        super.setCredentialsMatcher(shaCredentialsMatcher);
    }

    public static void main(String[] args) {
        String md5pass = "9221ee6fa6dfecaea6c68da5226248bb";
        String salt = "YzcmCZNvbXocrsz9dm8e";
        String newPassword = new SimpleHash(ShiroUtils.hashAlgorithmName,md5pass,salt,ShiroUtils.hashIterations).toHex();
        System.out.println(newPassword);
    }
}
