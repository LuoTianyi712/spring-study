package com.neusoft.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserTwo {
    private String name;

    public UserTwo(){
        System.out.println("UserT被创建了");
    }

    public void show(){
        System.out.println("name="+name);
    }
}
