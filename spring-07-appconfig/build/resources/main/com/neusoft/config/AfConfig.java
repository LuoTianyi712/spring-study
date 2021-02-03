package com.neusoft.config;

import com.neusoft.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration  // 这个也会被Spring托管，注册到容器中，因为这个注解本质也是@Component
                //代表这个是一个配置类，等价于Spring配置文件
@ComponentScan("com.neusoft.pojo")
@Import(AConfig.class)
public class AfConfig {

    // 注册一个bean，就相当于之前写的bean，
    // id为方法名 bean中的class就相当于返回值
    @Bean
    public User getUser(){
        return new User();  // 返回要注入到bean的对象
    }
}
