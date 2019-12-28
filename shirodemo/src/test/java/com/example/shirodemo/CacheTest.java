package com.example.shirodemo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheTest {


    @Test
    public void testRealmCache(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-cache.ini");

        SecurityUtils.setSecurityManager(factory.getInstance());
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang","1234");
        currentUser.login(token);

        System.out.println("查看日志");
    }

    @Test
    public void testRedisCache(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-cache-redis.ini");

        SecurityUtils.setSecurityManager(factory.getInstance());
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang","1234");
        currentUser.login(token);

        System.out.println("查看日志");
    }
    //    @Test
//    public void testClearCachedAuthenticationInfo() {
//        Subject subject = login("classpath:shiro-hashedCredentialsMatcher.ini", "admin", "123456");
//
//        userService.changePassword(u1.getId(), password + "1");
//        RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
//        MyAuthorizingRealm2 userRealm = (MyAuthorizingRealm2) securityManager.getRealms().iterator().next();
//        userRealm.clearCachedAuthenticationInfo(subject().getPrincipals());
//        login("classpath:shiro-hashedCredentialsMatcher.ini", "admin", "123456");
//    }
}
