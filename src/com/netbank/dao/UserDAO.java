package com.netbank.dao;

import com.netbank.entity.Account;
import com.netbank.entity.Admin;
import com.netbank.entity.Status;

/**
 * Created by dormi on 2017/1/15.
 */
public interface UserDAO {

    //修改账户
    public boolean updateAccount(Account account);
    public void reflush(Account account);
    //根据账户id获取账户对象
    public Account getAccount(int accountid);
    //根据客户名获取客户对象
    public Account getAccount(String username);
    //根据username获取管理员
    public Admin getAdmin(String username);
    //根据账户状态名称获取账户状态对象
    public Status getStatus(String name);

    public Status getStatus(int id);

    //添加账户
    public boolean addAccount(Account account);

    //删除账户
    public boolean delAccount(Account account);

    public boolean modifyAdmin(Admin admin);

}
