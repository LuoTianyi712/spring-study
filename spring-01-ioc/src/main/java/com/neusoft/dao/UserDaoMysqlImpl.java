package com.neusoft.dao;

public class UserDaoMysqlImpl implements UserDao{
    @Override
    public void getUser() {
        System.out.println("默认获取Mysql数据");
    }
}
