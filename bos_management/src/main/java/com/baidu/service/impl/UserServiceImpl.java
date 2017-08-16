package com.baidu.service.impl;

import com.baidu.dao.base.UserRepository;
import com.baidu.domain.User;
import com.baidu.service.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ZhangPei on 2017/8/13.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository ur;

    //查找用户是否存在
    @Override
    public User findByUsername(String username) {
        User user = ur.findByUsername(username);
        return user;
    }

    //查询所有用户
    @Override
    public Page<User> findAll(Pageable pageable) {
        Page<User> page = ur.findAll(pageable);
        return page;
    }

    //保存用户
    @Override
    public void saveUser(User model) {
        ur.save(model);
    }
}
