package com.baidu.dao.base;

import com.baidu.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ZhangPei on 2017/8/14.
 */
public interface MenuRepository extends JpaRepository<Menu,Integer> {
}
