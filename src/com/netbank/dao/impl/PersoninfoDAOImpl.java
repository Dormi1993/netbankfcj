package com.netbank.dao.impl;

import com.netbank.dao.PersoninfoDAO;
import com.netbank.entity.Personinfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
}
