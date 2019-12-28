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

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShirodemoApplicationTests {

	@Test
	public void contextLoads() {

	}

	// Shiro 身份验证 : https://www.w3cschool.cn/shiro/xgj31if4.html
	@Test
	public void testHelloworld() {

		//1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
//		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-custom-realm.ini");
//		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-multi-realm.ini");
//		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-authenticator-all-success.ini");

		//2、得到SecurityManager实例 并绑定给SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		//3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();

		// 对主体对象进行认证
		// 用户登陆
		// 设置用户认证的身份(principals)和凭证(credentials)
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
		try {
			//4、登录，即身份验证
			subject.login(token);
		} catch (AuthenticationException e) {
			//5、身份验证失败
			e.printStackTrace();
		}

		Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录

		//6、退出
		subject.logout();
	}


}
