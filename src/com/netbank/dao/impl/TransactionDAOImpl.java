package com.netbank.dao.impl;

import com.netbank.dao.TransactionDAO;
import com.netbank.entity.TransactionLog;
import com.netbank.entity.TransactionType;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by dormi on 2017/1/19.
 */
public class TransactionDAOImpl implements TransactionDAO {
    SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 根据交易类型名称获取交易类型对象
     * @param name
     * @return
     */
    @Override
    public TransactionType getTransactionType(String name) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from TransactionType t where t.name='" + name + "'";
        Query query = session.createQuery(hql);
        return (TransactionType) query.uniqueResult();
    }

    //向数据表transaction_log中添加记录
    @Override
    public boolean addLog(TransactionLog log) {

        Session session = sessionFactory.getCurrentSession();
        session.save(log);
        return true;
    }
}
