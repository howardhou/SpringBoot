package com.example.shirodemo.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

import java.io.Serializable;

//  SessionManager将session CRUD的工作委托给SessionDAO, 可以用特定的数据源API实现SessionDAO，以将session存储于任何一种数据源中
// 实现 CachingSessionDAO 类中的抽象方法，可以对Session进行持久化
public class MySessionDAO extends CachingSessionDAO {
    @Override
    protected void doUpdate(Session session) {

    }

    @Override
    protected void doDelete(Session session) {

    }

    @Override
    protected Serializable doCreate(Session session) {
        return null;
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        return null;
    }
}
