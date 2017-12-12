package com.netbank.dao.impl;

import com.netbank.dao.PersoninfoDAO;
import com.netbank.entity.Personinfo;
import com.netbank.entity.Status;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by dormi on 2017/1/19.
 */
public class PersoninfoDAOImpl implements PersoninfoDAO {
    SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    /**
     * 修改个人信息
     * @param personinfo
     */
    @Override
    public void modifyPersoninfo(Personinfo personinfo) {
        Session session = sessionFactory.getCurrentSession();
        session.update(personinfo);

    }

    /**
     * 查询全部用户信息
     */
    public List getAllPersoninfo() {
        Session session=sessionFactory.getCurrentSession();
        String hql="from Personinfo";
        Query query = session.createQuery(hql);
        return query.list();
    }

    /**
     * 根据账户状态获取用户信息
     */
    public List searchPersoninfo(Status status) {
        Session session=sessionFactory.getCurrentSession();
        String hql="from Personinfo p where p.account.status.id="+status.getId();
        Query query = session.createQuery(hql);
        return query.list();
    }
}
