package com.netbank.biz.impl;

import com.netbank.biz.UserBiz;
import com.netbank.dao.UserDAO;
import com.netbank.entity.Account;
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
    public boolean modifyAccount(Account account) {
        return userDAO.updateAccount(account);
    }

    @Override
    public void reflush(Account account) {
        userDAO.reflush(account);
    }
}
