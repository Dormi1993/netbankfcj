package com.netbank.dao;

import com.netbank.entity.TransactionLog;
import com.netbank.entity.TransactionType;

/**
 * Created by dormi on 2017/1/19.
 */
public interface TransactionDAO {
    //根据交易类型名称获取交易类型对象
    public TransactionType getTransactionType(String name);
    //根据数据表transaction_log中添加交易记录
    public boolean addLog(TransactionLog log);
}
