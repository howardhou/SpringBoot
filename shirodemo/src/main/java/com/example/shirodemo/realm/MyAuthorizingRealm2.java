package com.example.shirodemo.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/// 使用 HashedCredentialsMatcher 实现密码验证服务 ：可以提供自己的盐，而不是随机生成盐，且生成密码散列值的算法需要自己写

public class MyAuthorizingRealm2 extends AuthorizingRealm{

    // 设置realm的名称
    @Override
    public void setName(String name) {
        super.setName("myAuthorizingRealm2");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        // 从 principals获取主身份信息 , 用户名
        String userCode = (String) principalCollection.getPrimaryPrincipal();

        System.out.println("userCode：" + userCode);
        System.out.println("根据 userCode 查询数据库，获得权限配置");

        // 设置权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String username = (String)authenticationToken.getPrincipal();

        //如果用户名错误
        if(!"admin".equals(username)) {
            throw new UnknownAccountException();
        }

//        username = "admin"; //用户名及salt1
        String password = "ef0bcc113c8184f1c4b6f4225edf0f41"; //加密后的密码
        String salt2 = "9a8f3ae66be9dcd2e291516a973a069e";
        SimpleAuthenticationInfo ai = new SimpleAuthenticationInfo(username, password, getName());
        ai.setCredentialsSalt(ByteSource.Util.bytes(username+salt2)); //盐是用户名+随机数
        return ai;

    }

    public static void getEncodePassword(){
        String algorithmName = "md5";
        String username = "admin";
        String password = "123456";
        String salt1 = username;
        String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
        int hashIterations = 2;

        SimpleHash hash = new SimpleHash(algorithmName, password, salt1 + salt2, hashIterations);
        String encodedPassword = hash.toHex();

        System.out.printf("salt2 : " + salt2 + "  encodedPassword: " + encodedPassword + "\n");
    }


}
