package com.baidu.service.base;

import com.baidu.domain.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by ZhangPei on 2017/8/14.
 */
public interface PermissionService {
    //查找所有权限，带分页
    Page<Permission> findPermissionsByPaging(Pageable pageable);

    //保存权限
    void addPermission(Permission model);

    //查询所有权限，不带分页
    List<Permission> findAllPermissions();

}
