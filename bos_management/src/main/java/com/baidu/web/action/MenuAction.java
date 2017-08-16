package com.baidu.web.action;

import com.baidu.domain.Menu;
import com.baidu.domain.User;
import com.baidu.service.base.MenuService;
import com.baidu.web.action.base.BaseAction;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
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

import java.util.List;


/**
 * Created by ZhangPei on 2017/8/14.
 */
@ParentPackage("json-default")
@Namespace("/")
@Controller
@Scope("prototype")
public class MenuAction extends BaseAction<Menu> {

    @Autowired
    private MenuService ms;

    //查询菜单
    @Action(value = "findMenusByPaging", results = {@Result(name = "success", type = "json")})
    public String findMenusByPaging() {
            String pageSting = ServletActionContext.getRequest().getParameter("page");
            Integer page = Integer.parseInt(pageSting);

            Pageable pageable = new PageRequest(page - 1, rows);        //这块和菜单实体中的page重复了。
            Page<Menu> pageResult = ms.findMenusByPaging(pageable);
            pushResultToValueStack(pageResult);
            return SUCCESS;
    }

    //添加菜单
    @Action(value = "addMenu", results = {@Result(name = "success", type = "redirect", location = "./pages/system/menu.html"), @Result(name = "fail", type = "redirect", location = "./other/erro.html")})
    public String saveMenu() {
        if (model == null) {
            return FAIL;
        }
        ms.saveMenu(model);
        return SUCCESS;
    }

    //菜单授权
    @Action(value = "findMenus", results = {@Result(name = "success", type = "json")})
    public String loadMenus() {
        List<Menu> list = ms.findMenus();
        ServletActionContext.getContext().getValueStack().push(list);
        return SUCCESS;
    }

    //加载不同用户菜单
    @Action(value = "loadUserMenus", results = {@Result(name = "success", type = "json")})
    public String loadUserMenus() {
        //拿到当前登录用户
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        List<Menu> list = ms.loadUserMenus(user);
        ServletActionContext.getContext().getValueStack().push(list);
        return SUCCESS;
    }
}
