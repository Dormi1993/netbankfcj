package com.netbank.dao;

import com.netbank.entity.Account;

/**
 * Created by dormi on 2017/1/15.
 */
public interface UserDAO {
    //根据客户名获取账户对象
    public Account getAccount(String username);
    //修改账户
    public boolean updateAccount(Account account);
    public void reflush(Account account);
    //根据账户id获取账户对象
    public Account getAccount(int accountid);
}
