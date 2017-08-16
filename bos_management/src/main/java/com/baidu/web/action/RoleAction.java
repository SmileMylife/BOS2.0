package com.baidu.web.action;

import com.baidu.domain.Role;
import com.baidu.service.base.RoleService;
import com.baidu.web.action.base.BaseAction;
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
 * Created by ZhangPei on 2017/8/15.
 */
@ParentPackage("json-default")
@Namespace("/")
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {
    @Autowired
    private RoleService rs;

    //查找所有角色
    @Action(value = "findRolesByPaging", results = {@Result(name = "success", type = "json")})
    public String findRolesByPaging() {
        Pageable pageable = new PageRequest(page - 1, rows);
        Page<Role> page = rs.findRolesByPaging(pageable);
        pushResultToValueStack(page);
        return SUCCESS;
    }

    //角色数据添加
    private String ids;

    public void setIds(String ids) {
        this.ids = ids;
    }

    @Action(value = "addRole", results = {@Result(name = "success", type = "redirect", location = "./pages/system/role.html")})
    public String addRole() {
        String[] permissionArr = ServletActionContext.getRequest().getParameterValues("permissionIds");
        rs.addRole(ids,permissionArr,model);
        return SUCCESS;
    }

    //查询所有角色
    @Action(value = "findAllRoles",results = {@Result(name = "success",type = "json")})
    public String findAllRoles() {
        List<Role> list = rs.findAllRoles();
        ServletActionContext.getContext().getValueStack().push(list);
        return SUCCESS;
    }
}
