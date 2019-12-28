package com.example.shirodemo.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

// 通过SessionListener接口或者SessionListenerAdapter来进行session监听，在session创建、停止、过期时按需进行操作
public class MySessionListener implements SessionListener {

    @Override
    public void onStart(Session session) {
        System.out.println(session.getId()+" start...");
    }

    @Override
    public void onStop(Session session) {
        System.out.println(session.getId()+" stop...");
    }

    @Override
    public void onExpiration(Session session) {
        System.out.println(session.getId()+" expired...");
    }
}
