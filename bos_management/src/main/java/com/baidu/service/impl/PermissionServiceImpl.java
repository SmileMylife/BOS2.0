package com.baidu.service.impl;

import com.baidu.dao.base.PermissionRepository;
import com.baidu.domain.Permission;
import com.baidu.service.base.PermissionService;
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
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionRepository pr;

    //查找所有权限，并分页
    @Override
    public Page<Permission> findPermissionsByPaging(Pageable pageable) {
        Page<Permission> page = pr.findAll(pageable);
        return page;
    }

    //保存权限
    @Override
    public void addPermission(Permission model) {
        pr.save(model);
    }

    //查找所有权限，并以list返回
    @Override
    public List<Permission> findAllPermissions() {
        List<Permission> list = pr.findAll();
        return list;
    }
}
