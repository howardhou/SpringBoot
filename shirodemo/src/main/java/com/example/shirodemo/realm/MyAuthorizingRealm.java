package com.example.shirodemo.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/// DefaultPasswordService 配合 PasswordMatcher 实现简单的密码加密与验证服务
public class MyAuthorizingRealm extends AuthorizingRealm{
    private PasswordService passwordService;
    public void setPasswordService(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    // 设置realm的名称
    @Override
    public void setName(String name) {
        super.setName("myAuthorizingRealm");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        // 从 principals获取主身份信息 , 用户名
        // 将getPrimaryPrincipal方法返回值转为真实身份类型（在上边的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中身份类型），
        String userCode = (String) principalCollection.getPrimaryPrincipal();

        System.out.println("userCode：" + userCode);
        System.out.println("根据 userCode 查询数据库，获得权限配置");

        // 设置权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //1. 到数据库检查 username 是否存在，并获取 username 的状态
        String username = (String)authenticationToken.getPrincipal();

        //如果用户名错误
        if(!"admin".equals(username)) {
            throw new UnknownAccountException();
        }

        //2. 获取密码 , 可以从 DB 中获取 或者 API 中获取
        String password = "123456";
        //对密码进行加密处理
        password = passwordService.encryptPassword(password);

        /* 以下注释的 code 不需要，因为自定义的Realm继承自 AuthorizingRealm;
        AuthenticatingRealm 使用 CredentialsMatcher 进行判断密码是否匹配，如果不匹配将抛出密码错误异常 IncorrectCredentialsException；
        另外如果密码重试此处太多将抛出超出重试次数异常 ExcessiveAttemptsException；

        //得到密码
        String password = new String((char[])authenticationToken.getCredentials());

        //如果密码错误
        if(!"123".equals(password)) {
            throw new IncorrectCredentialsException();
        }
        */

        //如果身份认证验证成功，返回一个AuthenticationInfo实现；
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
