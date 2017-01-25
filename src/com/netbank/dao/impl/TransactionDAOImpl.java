package com.netbank.dao.impl;

import com.netbank.dao.TransactionDAO;
import com.netbank.entity.Account;
import com.netbank.entity.TransactionLog;
import com.netbank.entity.TransactionType;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

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

    /**
     * 获取交易记录
     * @param account
     * @param page
     * @return
     */
    @Override
    public List getLogs(Account account, int page) {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(TransactionLog.class);
        c.add(Restrictions.or(Restrictions.eq("account", account),
                Restrictions.eq("otherid", account.getAccountid())));
        c.addOrder(Order.desc("id"));
        c.setFirstResult(10 * (page - 1));
        c.setMaxResults(10);
        return c.list();
    }

    /**
     * 从数据表Transaction_Log中获取与账户相关的交易记录数
     * @param account
     * @return
     */
    @Override
    public Integer getCountOfLogs(Account account) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "select count(*) from Transaction_Log where (accountid="
        +account.getAccountid() + " or otherid="+ account.getAccountid() + ")";
        Query query = session.createSQLQuery(sql);
        Integer count = Integer.valueOf(query.uniqueResult().toString());
        return count;

    }
}
