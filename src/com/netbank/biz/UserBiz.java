package com.netbank.biz;

import com.netbank.entity.Account;
import com.netbank.entity.Admin;

/**
 * Created by dormi on 2017/1/16.
 */
public interface UserBiz {

    //根据账户名称获取账户
    public Account getAccount(String username);
    //修改账户
    public abstract boolean modifyAccount(Account account);
    public abstract void reflush(Account account);
    public abstract Account getAccount(int accountid);
    /**
     * 启用账户
     */
    public void enabled(int id);
    /**
     * 冻结账户
     */
    public void locking(int id);

    /**
     * 添加账户
     * @param account
     * @return
     */
    public boolean addAccount(Account account);

    /**
     * 删除账户
     */
    public boolean delAccount(int id);

    //修改管理员
    public boolean modifyAdmin(Admin admin);

    //根据username获取管理员
    public abstract Admin getAdmin(String username);
}
