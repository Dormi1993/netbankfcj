package com.netbank.biz;

import com.netbank.entity.Account;

/**
 * Created by dormi on 2017/1/16.
 */
public interface UserBiz {

    //根据账户名称获取账户
    public Account getAccount(String username);
    //修改账户
    public abstract boolean modifyAccount(Account account);
}
