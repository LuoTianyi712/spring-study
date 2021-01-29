package com.neusoft.pojo;

import lombok.Data;

@Data
public class User {
    private String name;

    public User(){
        System.out.println("User的无参构造");
    }

    public void show(){
        System.out.println("name="+name);
    }
}
