package com.example.shirodemo;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SessionTest {

    @Test
    public void testSession(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-sessions.ini");

        SecurityUtils.setSecurityManager(factory.getInstance());
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
        currentUser.login(token);

        Session session = currentUser.getSession();
        System.out.println("host : " + session.getHost());
        System.out.println("session id: " + session.getId());

        System.out.println("start time: " + session.getStartTimestamp());
        System.out.println("last access time: " + session.getLastAccessTime());

        session.touch();
//        User u = new User();
//        session.setAttribute(u, "King.");
//        Iterator<Object> keyItr = session.getAttributeKeys().iterator();
//        while(keyItr.hasNext()){
//            System.out.println(session.getAttribute(keyItr.next()));
//        }
    }

    @Test
    public void testRedisSession(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-sessions-redis.ini");

        SecurityUtils.setSecurityManager(factory.getInstance());
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
        currentUser.login(token);

        Session session = currentUser.getSession();
        System.out.println("host : " + session.getHost());
        System.out.println("session id: " + session.getId());

        System.out.println("start time: " + session.getStartTimestamp());
        System.out.println("last access time: " + session.getLastAccessTime());

        session.touch();
    }
}
