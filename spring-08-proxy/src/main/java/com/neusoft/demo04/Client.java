package com.neusoft.demo04;

import com.neusoft.demo02.UserService;
import com.neusoft.demo02.UserServiceImpl;

public class Client {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        // 代理角色 不存在
        ProxyInvocationHandler handler = new ProxyInvocationHandler();
        handler.setTarget(userService); // 设置要代理的对象
        // 动态生成代理类
        UserService proxy = (UserService) handler.getProxy();
        proxy.add();
    }
}
