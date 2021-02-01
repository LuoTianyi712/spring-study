package com.neusoft.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Getter
@Setter
@ToString
public class People {
    private String name;
    @Autowired
    @Qualifier(value = "cat111")
    private Cat cat;
    @Autowired
    private Dog dog;
}
