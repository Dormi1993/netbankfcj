package com.netbank.biz.impl;

import com.netbank.biz.PersoninfoBiz;
import com.netbank.dao.PersoninfoDAO;
import com.netbank.dao.UserDAO;
import com.netbank.entity.Personinfo;
import com.netbank.entity.Status;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 根据账户状态获取个人信息，状态为0表示获取所有客户
     */
    public List searchPersoninfo(Status status)
    {
        List users =new ArrayList();
        if(status.getId()!=0){
            //如果账户状态编号不为0，则根据编号获取相应客户记录
            status=userDAO.getStatus(status.getId());
            users=personinfoDAO.searchPersoninfo(status);
        }else{
            //如果账户状态编号等于0，则获取所有客户记录
            users= personinfoDAO.getAllPersoninfo();
        }
        return users;
    }

    /**
     * 根据条件获取个人信息
     */
    public List searchPersoninfo(Personinfo personinfo) {
        return personinfoDAO.searchPersoninfo(personinfo);
    }

    /**
     * 添加个人信息
     */
    public boolean add(Personinfo personinfo) {
        return personinfoDAO.add(personinfo);
    }

    /**
     * 查询个人信息
     */
    public List getAllPersoninfo() {
        return personinfoDAO.getAllPersoninfo();
    }


}
