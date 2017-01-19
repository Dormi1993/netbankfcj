package com.netbank.biz.impl;

import com.netbank.biz.PersoninfoBiz;
import com.netbank.dao.PersoninfoDAO;
import com.netbank.dao.UserDAO;
import com.netbank.entity.Personinfo;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dormi on 2017/1/19.
 */
//使用@Transactional注解实现事务管理
@Transactional
public class PersoninfoBizImpl implements PersoninfoBiz {
    //使用PersoninfoDAO接口定义对象，并添加set方法，用于依赖注入
    PersoninfoDAO personinfoDAO;

    public void setPersoninfoDAO(PersoninfoDAO personinfoDAO) {
        this.personinfoDAO = personinfoDAO;
    }
    //使用UserDAO接口定义对象，并添加set方法，用于依赖注入
    UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    //修改个人信息
    @Override
    public boolean modifyPersoninfo(Personinfo personinfo) {
        personinfoDAO.modifyPersoninfo(personinfo);
        return true;
    }
}
