package com.netbank.dao;

import com.netbank.entity.Account;
import com.netbank.entity.TransactionLog;
import com.netbank.entity.TransactionType;

import java.util.List;

/**
 * Created by dormi on 2017/1/19.
 */
public interface TransactionDAO {
    //根据交易类型名称获取交易类型对象
    public TransactionType getTransactionType(String name);
    //根据数据表transaction_log中添加交易记录
    public boolean addLog(TransactionLog log);
    //根据待显示页页码和账户对象获取交易记录
    public List getLogs(Account account, int page);
    //获取交易记录数
    public Integer getCountOfLogs(Account account);
}
