package com.baidu.service.base;

import com.baidu.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by ZhangPei on 2017/8/13.
 */
public interface UserService {

    User findByUsername(String username);

    Page<User> findAll(Pageable pageable);

    void saveUser(User model);
}
