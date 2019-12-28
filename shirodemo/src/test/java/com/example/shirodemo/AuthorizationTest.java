package com.example.shirodemo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

//授权测试
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorizationTest {

    private Subject login(String iniPath, String username, String password){
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(iniPath);

        //2、得到SecurityManager实例 并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();

        // 对主体对象进行认证
        // 用户登陆
        // 设置用户认证的身份(principals)和凭证(credentials)
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            //4、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //5、身份验证失败
            e.printStackTrace();
        }

        return subject;
    }

    @Test
    public void testHasRole() {

        Subject subject = login("classpath:shiro-role.ini", "zhang", "123");

        //判断拥有角色：role1
        Assert.assertTrue(subject.hasRole("role1"));
        //判断拥有角色：role1 and role2
        Assert.assertTrue(subject.hasAllRoles(Arrays.asList("role1", "role2")));
        //判断拥有角色：role1 and role2 and !role3
        boolean[] result = subject.hasRoles(Arrays.asList("role1", "role2", "role3"));
        Assert.assertEquals(true, result[0]);
        Assert.assertEquals(true, result[1]);
        Assert.assertEquals(false, result[2]);
    }

    @Test(expected = UnauthorizedException.class)
    public void testCheckRole() {
        Subject subject = login("classpath:shiro-role.ini", "zhang", "123");
        //断言拥有角色：role1
        subject.checkRole("role1");
        //断言拥有角色：role1 and role3 失败抛出异常
        subject.checkRoles("role1", "role3");
    }

    // 配置文件授权
    // 被许可，被允许，权限
    @Test
    public void testIsPermitted() {
        Subject subject = login("classpath:shiro-role.ini", "zhang", "123");

        // 基于资源授权
        System.out.println("是否拥有某一个权限：" + subject.isPermitted("user:create"));
        System.out.println("是否拥有多个权限：" + subject.isPermittedAll("user:create:1",	"user:delete"));

        //判断拥有权限：user:create
        Assert.assertTrue(subject.isPermitted("user:create"));
        //判断拥有权限：user:update and user:delete
        Assert.assertTrue(subject.isPermittedAll("user:update", "user:delete"));
        //判断没有权限：user:view
        Assert.assertFalse(subject.isPermitted("user:view"));
    }

    // Permission 许可， 权限 的意思
    @Test(expected = UnauthorizedException.class)
    public void testCheckPermission () {
        Subject subject = login("classpath:shiro-role.ini", "zhang", "123");
        //断言拥有权限：user:create
        subject.checkPermission("user:create");
        //断言拥有权限：user:delete and user:update
        subject.checkPermissions("user:delete", "user:update");
        //断言拥有权限：user:view 失败抛出异常
        subject.checkPermissions("user:view");
    }

    // 自定义 Realm 测试
    @Test
    public void testIsAuthenticated(){
        Subject subject = login("classpath:shiro-authorizer.ini", "zhang", "1234");

        //断言用户已经登录
        Assert.assertEquals(true, subject.isAuthenticated());
    }

    @Test
    public void testIsPermitted2() {
        Subject subject = login("classpath:shiro-authorizer.ini", "zhang", "1234");

        Assert.assertTrue(subject.isPermitted("user:create"));
        //判断拥有权限：user:update and user:delete
        Assert.assertTrue(subject.isPermittedAll("user:update", "user:delete"));
        //判断没有权限：user:view
        Assert.assertFalse(subject.isPermitted("user:view"));
    }
}
