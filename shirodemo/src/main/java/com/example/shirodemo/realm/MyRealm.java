package com.example.shirodemo.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyRealm extends AuthorizingRealm{


    // 设置realm的名称
    @Override
    public void setName(String name) {
        super.setName("myRealm");
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
        authorizationInfo.addRole("role1");
        authorizationInfo.addRole("role2");
//        authorizationInfo.addObjectPermission(new BitPermission("+user1+10"));
//        authorizationInfo.addObjectPermission(new WildcardPermission("user1:*"));
        authorizationInfo.addStringPermission("user:create");
        authorizationInfo.addStringPermission("user:update");
        authorizationInfo.addStringPermission("user:delete");

//        List<String> permissions = new ArrayList<String>();
//        permissions.add("user:create");// 用户的创建
//        permissions.add("user:update");
//        permissions.add("user:delete");
//        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //1. 到数据库检查 username 是否存在，并获取 username 的状态
        String username = (String)authenticationToken.getPrincipal();
        //如果用户名错误
        if(!"zhang".equals(username)) {
            throw new UnknownAccountException();
        }

        //2. 获取密码 , 可以从 DB 中获取 或者 API 中获取
        String password = "1234";

        /* 以下注释的 code 不需要，因为自定义的Realm继承自 AuthorizingRealm; 密码的判断会在 父类中 完成

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
