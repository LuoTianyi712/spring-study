package com.neusoft.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// 该注解等价于 <bean id="user" class="com.neusoft.pojo.User"/>
// 由于未在Spring配置文件中设定名字，默认名字为user

@Component
public class User {
    // 等价于 <property name="name" value="AFF">
    @Value("AFF")
    public String name;

    @Value("AF12")
    public void setName(String name) {
        this.name = name;
    }
}
