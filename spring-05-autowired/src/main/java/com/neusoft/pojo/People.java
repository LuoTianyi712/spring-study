package com.neusoft.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class People {
    private String name;
    private Cat cat;
    private Dog dog;
}
