package com.baidu.service.impl;

import com.baidu.dao.base.MenuRepository;
import com.baidu.dao.base.PermissionRepository;
import com.baidu.dao.base.RoleRepository;
import com.baidu.domain.Menu;
import com.baidu.domain.Permission;
import com.baidu.domain.Role;
import com.baidu.domain.User;
import com.baidu.service.base.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ZhangPei on 2017/8/14.
 */
@Service
@Transactional
public class
RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository rr;
    @Autowired
    private MenuRepository mr;
    @Autowired
    private PermissionRepository pr;

    //查询角色，通过用户
    @Override
    public List<Role> findByUsers(User user) {
        List<Role> list = rr.findByUsers(user);
        return list;
    }

    //查询所有角色
    @Override
    public Page<Role> findRolesByPaging(Pageable pageable) {
        Page<Role> page = rr.findAll(pageable);
        return page;
    }

    //添加角色
    @Override
    public void addRole(String ids, String[] permissionArr, Role model) {
        //菜单权限id
        String[] idsArr = ids.split("-");
        for (String s : idsArr) {
            Menu menu = mr.findOne(Integer.parseInt(s));
            model.getMenus().add(menu);
        }
        //添加权限
        if (permissionArr != null) {
            for (String s : permissionArr) {
                Permission permission = pr.findOne(Integer.parseInt(s));
                model.getPermissions().add(permission);
            }
        }
        rr.save(model);
    }

    //查询所有角色
    @Override
    public List<Role> findAllRoles() {
        List<Role> list = rr.findAll();
        return list;
    }
}
