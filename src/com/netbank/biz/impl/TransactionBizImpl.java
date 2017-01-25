package com.netbank.biz.impl;

import com.netbank.biz.TransactionBiz;
import com.netbank.dao.TransactionDAO;
import com.netbank.dao.UserDAO;
import com.netbank.entity.Account;
import com.netbank.entity.Pager;
import com.netbank.entity.TransactionLog;
import com.netbank.entity.TransactionType;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dormi on 2017/1/21.
 */
//使用@Transactional注解实现事务管理
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

    @Override
    public boolean withdrawal(TransactionLog log) {
        //从交易信息对象中log中取出账户对象
        Account self = log.getAccount();
        //将账户余额与存款金额相减
        self.setBalance(log.getAccount().getBalance() - log.getTrMoney());
        //更新账户表Account，修改账户余额
        userDAO.updateAccount(self);
        //根据交易类型获取交易类型对象
        TransactionType type = transactionDAO.getTransactionType("取款");
        log.setTransactionType(type);
        log.setOtherid(self.getAccountid());
        //向数据表transaction_log中添加交易记录
        return transactionDAO.addLog(log);
    }

    /**
     * 转账
     * @param log
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public boolean transfer(TransactionLog log) {
        //获取入账方账户对象
        Account other = userDAO.getAccount(log.getOtherid());
        //获取转账方账户对象
        Account self = log.getAccount();
        if (other != null){
            //修改转账方账户余额
            self.setBalance(log.getAccount().getBalance() - log.getTrMoney());
            //修改入账方账户余额
            other.setBalance(other.getBalance() + log.getTrMoney());
            //将转账方账户余额更新到数据表Account
            userDAO.updateAccount(self);
            //将入账方账户余额更新到数据表Account
            userDAO.updateAccount(other);
            //根据交易类型获取交易类型对象
            TransactionType type = transactionDAO.getTransactionType("转账");
            log.setTransactionType(type);
            //向数据表transaction_log中添加交易记录
            return transactionDAO.addLog(log);

        }
        return false;
    }

    //获取交易记录
    @Override
    public List getLogs(Account account, int page) {
        return transactionDAO.getLogs(account, page);
    }

    //获得账户的交易记录总数，用来初始化分页类Pager对象，并设置perPageRows和rowCount属性
    @Override
    public Pager getPagerOfLogs(Account account) {
        //从数据表Transaction_Log中获取与账户相关的交易记录数
        int count = transactionDAO.getCountOfLogs(account);
        //使用分页类Pager定义对象
        Pager pager = new Pager();
        //设置pager对象中的perPagerRows属性，表示每页显示的记录数
        pager.setPerPageRows(10);
        //设置pager对象中rowCount属性目标是记录总数
        pager.setPerPageRows(count);
        //返回pager对象
        return pager;
    }
}
