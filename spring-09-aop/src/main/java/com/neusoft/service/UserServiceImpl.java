package com.neusoft.service;

public class UserServiceImpl implements UserService{
    @Override
    public void add() {
        System.out.println("增加1个用户");
    }

    @Override
    public void delete() {
        System.out.println("删除1个用户");
    }

    @Override
    public void update() {
        System.out.println("修改1个用户");
    }

    @Override
    public void query() {
        System.out.println("查询1个用户");
    }
}
