package com.netbank.biz;

import com.netbank.entity.Account;
import com.netbank.entity.Pager;
import com.netbank.entity.TransactionLog;

import java.util.List;

/**
 * Created by dormi on 2017/1/21.
 */
public interface TransactionBiz {
    public boolean deposit(TransactionLog log);
    public boolean withdrawal(TransactionLog log);
    public boolean transfer(TransactionLog log);
    //获取交易记录
    public List getLogs(Account account, int page);
    //获得账户的交易记录总数，用来初始化分页类Pager对象，并设置perPageRows和rowCount属性
    public Pager getPagerOfLogs(Account account);
}
