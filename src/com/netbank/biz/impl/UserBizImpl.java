package com.netbank.biz.impl;

import com.netbank.biz.UserBiz;
import com.netbank.dao.UserDAO;
import com.netbank.entity.Account;
import com.netbank.entity.Admin;
import com.netbank.entity.Status;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dormi on 2017/1/16.
 */
//使用@Transactional注解实现事务管理
@Transactional
public class UserBizImpl implements UserBiz{

    //使用UserDao接口声明对象，并添加set方法，用于依赖注入
    UserDAO userDAO;
    public void setUserDAO(UserDAO userDAO){
        this.userDAO = userDAO;
    }


    //根据username获取账户
    @Override
    public Account getAccount(String username) {
        return userDAO.getAccount(username);
    }

    @Override
    public void reflush(Account account) {
        userDAO.reflush(account);
    }

    @Override
    //根据账户ID获取账户
    @Transactional(readOnly = true)
    public Account getAccount(int accountid) {
        return userDAO.getAccount(accountid);

    }

    //根据username获取管理员
    @Override
    public Admin getAdmin(String username) {
        return userDAO.getAdmin(username);
    }

    /**
     * 启用账户
     */
    public void enabled(int id) {
        //根据账户编号获取账户对象
        Account account = userDAO.getAccount(id);
        //修改账户对象的状态属性，设置为启用
        Status status = userDAO.getStatus("启用");
        account.setStatus(status);
        //更新账户
        userDAO.updateAccount(account);
    }

    /**
     * 冻结账户
     */
    public void locking(int id) {
        //根据账户编号获取账户对象
        Account account = userDAO.getAccount(id);
        //修改账户对象的状态属性，设置为冻结
        Status status = userDAO.getStatus("冻结");
        account.setStatus(status);
        //更新账户
        userDAO.updateAccount(account);

    }

    //添加账户
    public boolean addAccount(Account account) {
        Status status=userDAO.getStatus("启用");
        account.setStatus(status);
        return userDAO.addAccount(account);
    }

    /**
     * 删除用户
     */
    public boolean delAccount(int id) {
        //根据账户id获取账户
        Account account=userDAO.getAccount(id);
        //删除账户对象，同时执行级联删除
        return userDAO.delAccount(account);
    }

    //修改账户
    public boolean modifyAccount(Account account) {
        return userDAO.updateAccount(account);
    }

    //修改管理员
    public boolean modifyAdmin(Admin admin) {
        return userDAO.modifyAdmin(admin);
    }

}
