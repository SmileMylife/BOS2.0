package com.baidu.dao.base;

import com.baidu.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ZhangPei on 2017/8/14.
 */
public interface PermissionRepository extends JpaRepository<Permission,Integer> {

}
