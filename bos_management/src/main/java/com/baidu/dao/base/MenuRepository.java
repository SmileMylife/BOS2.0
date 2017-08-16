package com.baidu.dao.base;

import com.baidu.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by ZhangPei on 2017/8/14.
 */
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    @Query(value = "from Menu m inner join fetch m.roles r inner join fetch r.users u where u.id = ?1")
    List<Menu> loadUserMenus(Integer id);
}
