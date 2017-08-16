package com.baidu.web.action;

import com.baidu.dao.base.MenuRepository;
import com.baidu.dao.base.RoleRepository;
import com.baidu.domain.Menu;
import com.baidu.domain.Permission;
import com.baidu.domain.Role;
import com.baidu.domain.User;
import com.baidu.service.base.PermissionService;
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
@Scope("prototype")
@Controller
public class PermissionAction extends BaseAction<Permission> {
    @Autowired
    private PermissionService ps;

    //查询所有权限
    @Action(value = "findPermissionsByPaging", results = {@Result(name = "success", type = "json")})
    public String findPermissions() {
        String pageString = ServletActionContext.getRequest().getParameter("page");
        Integer page = Integer.parseInt(pageString);
        Pageable pageable = new PageRequest(page - 1, rows);
        Page<Permission> pageResult = ps.findPermissionsByPaging(pageable);
        pushResultToValueStack(pageResult);
        return SUCCESS;
    }

    //新增权限保存
    @Action(value = "addPermission", results = {@Result(name = "success", type = "redirect", location = "./pages/system/permission.html")})
    public String addPermission() {
        ps.addPermission(model);
        return SUCCESS;
    }

    //查询所有权限，并以list返回。
    @Action(value = "findAllPermissions", results = {@Result(name = "success", type = "json")})
    public String findAllPermissions() {
        List<Permission> list = ps.findAllPermissions();
        ServletActionContext.getContext().getValueStack().push(list);
        return SUCCESS;
    }

}
