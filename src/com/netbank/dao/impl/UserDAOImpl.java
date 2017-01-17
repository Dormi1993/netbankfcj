package com.netbank.dao.impl;

import com.netbank.dao.UserDAO;
import com.netbank.entity.Account;
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

    //根据username获取账户
//    @Override
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
}
