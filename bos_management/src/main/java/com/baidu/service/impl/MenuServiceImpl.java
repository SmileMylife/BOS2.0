package com.baidu.service.impl;

import com.baidu.dao.base.MenuRepository;
import com.baidu.domain.Menu;
import com.baidu.domain.User;
import com.baidu.service.base.MenuService;
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
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuRepository mr;

    //查询菜单
    @Override
    public Page<Menu> findMenusByPaging(Pageable pageable) {
        Page<Menu> page = mr.findAll(pageable);
        return page;
    }

    //添加菜单
    @Override
    public void saveMenu(Menu model) {
        mr.save(model);
    }

    //查找所有菜单，list返回
    @Override
    public List<Menu> findMenus() {
        List<Menu> list = mr.findAll();
        return list;
    }

    //加载用户拥有的菜单
    @Override
    public List<Menu> loadUserMenus(User user) {
        List<Menu> list = mr.loadUserMenus(user.getId());
        return list;
    }

}
