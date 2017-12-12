package com.netbank.dao.impl;

import com.netbank.dao.UserDAO;
import com.netbank.entity.Account;
import com.netbank.entity.Admin;
import com.netbank.entity.Status;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by dormi on 2017/1/15.
 */
public class UserDAOImpl implements UserDAO {

    SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    //根据username获取管理员
    public Admin getAdmin(String username) {
        Session session=sessionFactory.getCurrentSession();
        String hql="from Admin as a where a.username='"+username+"'";
        Query query = session.createQuery(hql);
        return (Admin) query.uniqueResult();
    }

    //根据username获取账户
    @Override
    public Account getAccount(String username) {
        Session session = sessionFactory.getCurrentSession();
        String hql="from com.netbank.entity.Account where username='"+username+"'";
        Query query = session.createQuery(hql);
        return (Account) query.uniqueResult();

    }

    @Override
    public boolean updateAccount(Account account) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(account);
        return true;
    }

    @Override
    public void reflush(Account account) {
        Session session = sessionFactory.getCurrentSession();
        session.refresh(account);
    }

    @Override
    public Account getAccount(int accountid) {
        Session session = sessionFactory.getCurrentSession();
        return (Account) session.get(Account.class, accountid);//注意这儿！
    }

    /**
     *根据名称获取状态
     */
    public Status getStatus(String name) {
        Session session=sessionFactory.getCurrentSession();
        String hql="from Status as s where s.name='"+name+"'";
        Query query = session.createQuery(hql);
        return (Status) query.uniqueResult();
    }

    public Status getStatus(int id) {
        Session session=sessionFactory.getCurrentSession();
        return (Status) session.get(Status.class, id);
    }
}
