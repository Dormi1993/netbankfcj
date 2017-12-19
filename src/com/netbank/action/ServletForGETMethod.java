package com.netbank.action;

/**
 * Created by Administrator on 2017/3/31 0031.
 */

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class ServletForGETMethod
 */
//@WebServlet("/fcj")
public class ServletForGETMethod extends ActionSupport implements
        ServletRequestAware, ServletResponseAware {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletForGETMethod() {
        super();
        // TODO Auto-generated constructor stub
    }

    private HttpServletRequest request11;
    private HttpServletResponse response11;

    public void setServletRequest(HttpServletRequest request) {
        this.request11=request;
    }

    public void setServletResponse(HttpServletResponse response) {
        this.response11=response;
    }

    @Override
    public String execute() throws Exception {
        System.out.println("requestfcjUsername=" + request11.getParameter("name") + ",requestPassword=" + request11.getParameter("pwd"));
        return "success";
    }

}