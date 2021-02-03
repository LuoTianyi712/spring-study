package com.neusoft.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component // 这个注解的意思是，说明这个类被被Spring接管了，注册到容器中
public class User {
    @Value("java") // 属性注入值
    private String name;
}
