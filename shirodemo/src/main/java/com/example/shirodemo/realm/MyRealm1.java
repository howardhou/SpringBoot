package com.example.shirodemo.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class MyRealm1 implements Realm{
    @Override
    public String getName() {
        return "myrealm1";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        //仅支持UsernamePasswordToken类型的Token
        return authenticationToken instanceof UsernamePasswordToken;
//      return false;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //1. 获得用户输入的用户名， 到数据库检查 username 是否存在，并获取 username 的状态
        String username = (String)authenticationToken.getPrincipal();

        //如果用户名错误
        if(!"zhang".equals(username)) {
            throw new UnknownAccountException();
        }

        //2. 获取用户输入的密码
        String password = new String((char[])authenticationToken.getCredentials());
        //根据用户名， 可以从 DB 中获取, 或 API接口 获得密码
        String savedPassword = "123";

        //如果密码错误,
        if(!savedPassword.equals(password)) {
            throw new IncorrectCredentialsException();
        }

        //如果身份认证验证成功，返回一个AuthenticationInfo实现；
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
