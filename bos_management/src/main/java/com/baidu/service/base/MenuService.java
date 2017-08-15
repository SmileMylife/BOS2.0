package com.baidu.service.base;

import com.baidu.domain.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by ZhangPei on 2017/8/14.
 */
public interface MenuService {
    //查找所有菜单项
    Page<Menu> findMenusByPaging(Pageable pageable);

    //保存菜单
    void saveMenu(Menu model);
}
