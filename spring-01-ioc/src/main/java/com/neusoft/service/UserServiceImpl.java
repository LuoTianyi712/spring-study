package com.neusoft.service;

import com.neusoft.dao.UserDao;

public class UserServiceImpl implements UserService{

    private UserDao userDao;

    // 利用set实现动态值注入
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void getUser() {
        userDao.getUser();
    }
}
