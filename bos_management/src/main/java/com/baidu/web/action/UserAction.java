package com.baidu.web.action;

import com.baidu.dao.base.UserRepository;
import com.baidu.domain.User;
import com.baidu.service.base.UserService;
import com.baidu.web.action.base.BaseAction;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;


/**
 * Created by ZhangPei on 2017/8/13.
 */
@ParentPackage("json-default")
@Namespace("/")
@Controller
@Scope("prototype")

public class UserAction extends BaseAction<User> {
    private String FAIL = "fail";
    @Autowired
    private UserService us;

    //登录系统
    @Action(value = "login", results = {@Result(name = "success", type = "redirect", location = "index.html"), @Result(name = "fail", type = "redirect", location = "login.html")})
    public String login() {
        if (model == null || model.getUsername() == null || model.getPassword() == null) {
            return FAIL;
        }
        AuthenticationToken authenticationToken = new UsernamePasswordToken(model.getUsername(), model.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(authenticationToken);     //这块先调用subject的login然后调用安全管理器再调用realm。
            return SUCCESS;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return FAIL;
        }
    }

    //退出系统
    @Action(value = "logOut", results = {@Result(name = "success", type = "redirect", location = "login.html")})
    public String logOut() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return SUCCESS;
    }

    //查询所有用户
    @Action(value = "findAllUsers", results = {@Result(name = "success", type = "json")})
    public String findAllUsers() {
        Pageable pageable = new PageRequest(page - 1,rows);
        Page<User> page = us.findAll(pageable);
        pushResultToValueStack(page);
        return SUCCESS;
    }

    //保存所有用户
    @Action(value = "saveUser",results = {@Result(name = "success",type = "redirect",location = "./pages/system/userindex.html")})
    public String saveUser() {
        us.saveUser(model);
        return SUCCESS;
    }
}
