package com.netbank.biz;

import com.netbank.entity.TransactionLog;

/**
 * Created by dormi on 2017/1/21.
 */
public interface TransactionBiz {
    public boolean deposit(TransactionLog log);
}
