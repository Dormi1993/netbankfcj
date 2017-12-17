package com.netbank.action;

import com.netbank.biz.UserBiz;
import com.netbank.entity.Account;
import com.netbank.entity.Password;
import com.netbank.entity.Personinfo;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by dormi on 2017/1/16.
 */
public class UserAction extends ActionSupport implements RequestAware, SessionAware {

    //定义通过@Resource注解注入的属性userBiz，可省略set方法
    @Resource private UserBiz userBiz;
    //但是下面两个需要有set方法，见最下面
    Map<String, Object> request;
    Map<String, Object> session;

    //定义Account类型对象，用于封装登录表单参数
    private Account account;
    private Personinfo personinfo;
    private Password pwd;

    //省略其他请求的处理方法？？？？

    public String logout(){
        session.remove("user");
        session.remove("personinfo");
        return "login";
    }

    /**
     * 登录表单校验，根据用户名获取账户对象
     * @return
     */
    public void validateLogin(){
        Account a = userBiz.getAccount(account.getUsername());
        if (a == null){
            this.addFieldError("username", "用户名不存在");
        } else {
            if (!account.getPassword().equals(a.getPassword())){
                this.addFieldError("password", "密码不正确");
            }
        }
        account = a;
    }

    /**
     *执行页面客户登录请求
     * @return
     */
    public String login(){
        //根据关联关系从账户对象中获取个人信息对象
        personinfo = (Personinfo) account.getPersoninfos().iterator().next();
        //将账户对象存入Session
        session.put("user", account);//haha
        //将该账户个人信息对象存入Session
        session.put("personinfo", personinfo);

        HttpServletRequest httpServletRequest = ServletActionContext.getRequest();
        String name = httpServletRequest.getParameter("name");
        String pwd = httpServletRequest.getParameter("pwd");

        System.out.println("haname:" + name + "hpwd: "+ pwd);
        //页面转发
        return null;
    }

    /**
     * 修改密码页面验证
     *
     */
    public void validateChangepwd(){
        account = (Account) session.get("user");
        if (! pwd.getOldpwd().equals(account.getPassword())){
            this.addFieldError("pwd.oldpwd", "密码不正确");
        }
        if (! pwd.getNewpwd().equals(pwd.getConfirmpwd())){
            this.addFieldError("pwd.confirmpwd", "两次密码不一致");
        }
    }

    /**
     * 执行修改密码请求
     * @return
     */
    public String changepwd(){
        account.setPassword(pwd.getNewpwd());
        if (userBiz.modifyAccount(account)){
            session.put("user", account);
            request.put("message33", "密码修改成功fafa！");
            return "message2";
        }
        request.put("message33", "密码修改失败！");
        return "message2";
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Personinfo getPersoninfo() {
        return personinfo;
    }

    public void setPersoninfo(Personinfo personinfo) {
        this.personinfo = personinfo;
    }

    //下面这俩不是set和get方法。。。
//    public Password getPassword() {
//        return pwd;
//    }
//
//    public void setPassword(Password password) {
//        this.pwd = password;
//    }

    public Password getPwd() {
        return pwd;
    }

    public void setPwd(Password pwd) {
        this.pwd = pwd;
    }

    @Override
    public void setRequest(Map<String, Object> request) {
        this.request = request;

    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;

    }
}
