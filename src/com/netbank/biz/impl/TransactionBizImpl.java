package com.netbank.biz.impl;

import com.netbank.biz.TransactionBiz;
import com.netbank.dao.TransactionDAO;
import com.netbank.dao.UserDAO;
import com.netbank.entity.Account;
import com.netbank.entity.TransactionLog;
import com.netbank.entity.TransactionType;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dormi on 2017/1/21.
 */
//使用@Transactional
@Transactional
public class TransactionBizImpl implements TransactionBiz {
    //使用TransactionDAO
    private TransactionDAO transactionDAO;

    public void setTransactionDAO(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    //使用UserDAO
    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * 
     * 存款
     * @param log
     * @return
     */
    @Override
    public boolean deposit(TransactionLog log) {
        //从交易信息对象log中取出账户对象
        Account self = log.getAccount();
        //将账户余额与存款金额相加
        self.setBalance(log.getAccount().getBalance() + log.getTrMoney());
        //更新账户表Account，修改账户余额
        userDAO.updateAccount(self);
        //根据交易类型获取交易类型对象
        TransactionType type = transactionDAO.getTransactionType("存款");
        log.setTransactionType(type);
        log.setOtherid(self.getAccountid());
        //向数据表transaction_log中添加交易记录
        return transactionDAO.addLog(log);
    }
}
