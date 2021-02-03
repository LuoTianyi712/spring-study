# Spring5

[TOC]

## 1、Spring

### 1.1、简介

- Spring -- 软件行业的春天
- 2002 Spring的前身--Interface21.io

- 2004 Spring出现，发布1.0版本
- Rod Johnson -- Spring创始人
- **理念：使现有技术更加容易使用，整合了现有的技术框架**



- SSH：Struct2 + Spring + Hibernate
- SSM：SpringMVC + Spring + Mybatis

官网：https://spring.io/projects/spring-framework

官方下载地址：https://repo.spring.io/release/org/springframework/spring/

官方文档：https://docs.spring.io/spring-framework/docs/current/reference/html/core.html

中文文档：https://www.docs4dev.com/docs/zh/spring-framework/5.1.3.RELEASE/reference

GitHub：https://github.com/spring-projects/spring-framework

build.gradle导入依赖包

```java
//webmvc -- 一个整合的包，包含了其他的子包
compile group: 'org.springframework', name: 'spring-webmvc', version: '5.3.3'
//JDBC
compile group: 'org.springframework', name: 'spring-jdbc', version: '5.3.3'
```

### 1.2、优点

- 开源免费框架（容器）
- 轻量级的，非入侵式框架
- **控制反转（IOC），面向切面编程（AOP）**
- 支持事务的处理，对框架整合的支持

总结：==**Spring是轻量级的控制反转（IOC）和面向切面编程（AOP）的框架**==

### 1.3、组成

![img](https://images2017.cnblogs.com/blog/1219227/201709/1219227-20170930225010356-45057485.gif)

　

### 1.4、扩展

![image-20210128145251084](C:\Users\LiuFeiyu\AppData\Roaming\Typora\typora-user-images\image-20210128145251084.png)

- spring boot
  - 一个快速开发的脚手架
  - 基于springboot可以快速开发单个微服务
  - 约定大于配置

- spring cloud
  - 基于springboot实现

为什么学习？

现在大多数公司都在使用springBoot进行快速开发
学习springBoot需要完全掌握Spring以及SpringMvc

弊端：发展过久，违背原先理念，配置繁琐【配置地狱】

## 2、IOC理论推导 -- Spring的核心内容

### 2.1、推导

原先实现一个业务

![image-20210129101348544](C:\Users\LiuFeiyu\AppData\Roaming\Typora\typora-user-images\image-20210129101348544.png)

1、UserDao接口

2、UserDaoImpl实现类

3、UserService业务接口

4、UserServiceImpl业务实现类

在之前的业务中，用户的需求可能会影响我们原来的代码，我们需要根据用戶的需求去修改原代码
如果程序代码量十分大，修改一次的成本代价十分昂贵

而如果使用一个Set接口实现，**代码会发生革命性的变化**

```java
// 利用set 进行 动态实现值的注入
public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
}
```

思想的转变：

![image-20210129101408314](C:\Users\LiuFeiyu\AppData\Roaming\Typora\typora-user-images\image-20210129101408314.png)

- 原来程序主动创建对象，控制权在程序员手上，
- 使用set注入后，程序不再具有主动性，而是变成了被动接收对象

这种思想，从本质上解决了问题，我们程序员不用再去管理对象的创建了，系统的耦合性大大降低，可以更加专注于业务的实现，这个就是IOC的原型

### 2.2、IOC本质

**控制反转loC(Inversion of Control)，是一种设计思想，Dl(依赖注入)是实现loC的一种方法**，也有人认为DI只是IoC的另一种说法。没有IoC的程序中，我们使用面向对象编程，对象的创建与对象间的依赖关系完全硬编码在程序中，对象的创建由程序自己控制，控制反转后将对象的创建转移给第三方，个人认为所谓控制反转就是：**获得依赖对象的方式反转了**。

采用XML方式配置Bean的时候，Bean的定义信息是和实现分离的，而采用注解的方式可以把两者合为一体，Bean的定义信息直接以注解的形式定义在实现类中，从而达到了零配置的目的。

**控制反转是一种通过描述（XML或注解）并通过第三方去生产或获取特定对象的方式。在 Spring中实现控制反转的是loC容器,其实现方法是依赖注入(Dependency Injection，DI)**

## 3、HelloSpring

### 3.1、Spring测试方法

- 创建pojo实体类【HelloWorld.java】

  ```java
  @Data
  public class HelloWorld {
      private String str;
  }
  ```

- resources目录下建立**Spring配置文件**【ApplicationContext.xml】

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
          https://www.springframework.org/schema/beans/spring-beans.xsd">
  
      <!--使用spring创建对象，在spring这些都称为bean-->
      <bean id="hello" class="com.neusoft.pojo.HelloWorld">
          <property name="str" value="Spring"/>
      </bean>
  
  </beans>
  ```

  配置一些元数据（beans，也称为对象）

- 编写测试类

  ```java
  public class ApplicationTest {
      public static void main(String[] args) {
          // 获取Spring上下文对象，可以配置多个xml
          ApplicationContext context =
                  new ClassPathXmlApplicationContext("ApplicationContext.xml");
          // 我们的对象现在都在Spring中管理了，要使用，直接去里面取出来就可以
          HelloWorld hello = (HelloWorld) context.getBean("hello");
          System.out.println(hello.toString());
      }
  }
  ```

输出结果

![image-20210129111755312](C:\Users\LiuFeiyu\AppData\Roaming\Typora\typora-user-images\image-20210129111755312.png)

### 3.2、关于Spring的问题

- Hello对象是谁创建的？

  Spring创建的

- Hello对象的属性（str=Spring）是怎么设置的？

  由Spring容器设置的，在Spring配置文件中设置的value

在**Sping配置文件**中，bean相当于对象

类型 变量名 = new 类型();
HelloWorld hello = new HelloWorld();

id = 变量名 ---> 类型
class = new的对象 ---> 变量名
property 相当于给对象中的属性设置一个值
bean=对象 ，能执行new HelloWorld()

**总结：在配置文件中编写Bean等价于new一个对象**

控制：谁来控制对象的创建，传统应用程序的对象是由程序本身控制创建的，使用 Spring后，对象是由 Spring来创建的

反转：程序本身不创建对象，而变成被动的接收对象

依赖注入：就是利用set方法来进行注入

IOC是一种编程思想,由主动的编程变成被动的接收

**OK，到了现在，我们彻底不用再程序中去改动了，要实现不同的操作，只需要在xm配置文件中进行修改，所谓的loC，一句话搞定：对象由 Spring来创建，管理，装配。**

### 3.3、将spring-01-ioc代码修改为Spring托管

模块一结构

![image-20210129134733518](C:\Users\LiuFeiyu\AppData\Roaming\Typora\typora-user-images\image-20210129134733518.png)

```xml
<!--使用spring创建对象，在spring这些都称为bean-->
<bean id="oracleImpl" class="com.neusoft.dao.UserDaoOracleImpl"/>
<bean id="mysqlImpl" class="com.neusoft.dao.UserDaoMysqlImpl"/>

<bean id="serviceImpl" class="com.neusoft.service.UserServiceImpl">
    <property name="userDao" ref="mysqlImpl"/>
</bean>

<!--
    ref：引用Spring容器中创建好的对象
    value：就是一个具体的值
-->
```

```java
public class ApplicationTest {
    public static void main(String[] args) {

        // 获取ApplicationContext：拿到Spring容器
        ApplicationContext context =
                new ClassPathXmlApplicationContext("ApplicationContext.xml");
        // 需要什么，就直接get什么
        UserServiceImpl serviceImpl = (UserServiceImpl) context.getBean("serviceImpl");

        serviceImpl.getUser();
    }
}
```

## 4、IOC创建对象的方式

### 4.1、使用无参构造创建对象 -- 默认实现方法

```xml
<bean id="user" class="com.neusoft.pojo.User">
    <property name="name" value="111"/>
</bean>
```

```java
public static void main(String[] args) {
    ApplicationContext context =
            new ClassPathXmlApplicationContext("ApplicationContext.xml");
    User user = (User) context.getBean("user");
    user.show();
}
```

输出结果

![image-20210129144444206](C:\Users\LiuFeiyu\AppData\Roaming\Typora\typora-user-images\image-20210129144444206.png)

### 4.2、使用有参构造创建对象

- 下标赋值法

  ```xml
  <bean id="user" class="com.neusoft.pojo.User">
      <!--下标赋值-->
      <constructor-arg index="0" value="A-10"/>
  </bean>
  ```

- 通过类型创建

  ```xml
  <bean id="user" class="com.neusoft.pojo.User">
      <constructor-arg type="java.lang.String" value="P-996"/>
  </bean>
  ```

- 直接通过参数名创建

  ```xml
  <bean id="user" class="com.neusoft.pojo.User">
      <constructor-arg name="name" value="apache"/>
  </bean>
  ```

```java
public static void main(String[] args) {
    ApplicationContext context =
            new ClassPathXmlApplicationContext("ApplicationContext.xml");

    // 此处使用--直接通过参数名创建
    User user = (User) context.getBean("user");
    user.show();

    User user2 = (User) context.getBean("user");
    System.out.println(user==user2);
}
```

输出结果

![image-20210129144812065](C:\Users\LiuFeiyu\AppData\Roaming\Typora\typora-user-images\image-20210129144812065.png)

在**Spring配置文件**加载的时候，容器中管理的对象就已经初始化了

## 5、Spring配置说明

### 5.1、别名

**Spring配置文件**中更改别名

```xml
<alias name="user" alias="fmjgksdl"/>
```

```java
public static void main(String[] args) {
    ApplicationContext context =
            new ClassPathXmlApplicationContext("ApplicationContext.xml");

    User user = (User) context.getBean("fmjgksdl");
    user.show();

}
```

如果添加了别名，也可以使用别名getBean获取到这个对象

### 5.2、Bean的配置 -- 核心

```xml
<!--
    id：Bean的唯一标识符，也就相当于对象名
    class：bean对象所对应的类型（全限定名）
    name：也是别名，可以同时取多个别名，可以通过空格，逗号，分号进行名称分隔
-->
<bean id="userTwo" class="com.neusoft.pojo.UserTwo" name="two u2">
    <property name="name" value="U47"/>
</bean>
```

### 5.3、Import 标签

一般用于团队开发，可以将多个配置文件，导入合并为一个

ApplicationContext.xml

```
<import resource="bean1.xml"/>
<import resource="bean2.xml"/>
<import resource="bean3.xml"/>
```

将bean1,bean2,bean3合并到ApplicationContext.xml

使用的时候，直接使用总配置就行了

## 6、依赖注入：DI -- 实现IOC

### 6.1、构造器注入

4.1章节即为之前提到的构造方法注入

参考**官方文档**1.4.1第一点**Constructor Argument Resolution**

### 6.2、Set方式注入 -- 重点

- 依赖注入：set注入
  - 依赖：bean对象的创建依赖于容器
  - 注入：bean对象中的所有属性，由容器来注入

【环境搭建】

- 复杂类型

  Address实体类

  ```java
  @Getter
  @Setter
  public class Address {
      private String address;
  }
  ```

- 真实测试对象

  Student实体类

  ```java
  @Getter
  @Setter
  @ToString
  public class Student {
      private String name;
      private Address address;
      private String[] book;
      private List<String> hobbies;
      private Map<String,String> card;
      private Set<String> games;
      private Properties info;
      private String wife;
  }
  ```

- beans.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
          https://www.springframework.org/schema/beans/spring-beans.xsd">
  
      <bean id="student" class="com.neusoft.pojo.Student">
          <!--第一种：普通值注入 设置value-->
          <property name="name" value="阿飞"/>
      </bean>
  
  </beans>
  ```

- 测试类

  ```java
  public class SpringApplicationTest {
      public static void main(String[] args) {
          ApplicationContext context =
                  new ClassPathXmlApplicationContext("beans.xml");
  
          Student student = (Student) context.getBean("student");
  
          System.out.println(student);
      }
  }
  ```

完善注入信息（beans.xml）

```xml
<!--复杂类型引用-->
<bean id="address" class="com.neusoft.pojo.Address">
    <property name="address" value="北京"/>
</bean>

<bean id="student" class="com.neusoft.pojo.Student">
    <!--第一种：普通值注入 设置value-->
    <property name="name" value="张三"/>

    <!--1：Bean注入，ref引用-->
    <property name="address" ref="address"/>

    <!--2：数组注入-->
    <property name="book">
        <array>
            <value>红楼梦</value>
            <value>西游记</value>
            <value>三国演义</value>
            <value>水浒传</value>
        </array>
    </property>

    <!--3:List注入-->
    <property name="hobbies">
        <list>
            <value>上网</value>
            <value>唱歌</value>
            <value>写作</value>
        </list>
    </property>

    <!--4:Map注入-->
    <property name="card">
        <map>
            <entry key="身份证" value="111111222222223333"/>
            <entry key="银行卡" value="2145654654231"/>
        </map>
    </property>

    <!--5:set注入-->
    <property name="games">
        <set>
            <value>英雄联盟</value>
            <value>绝地求生</value>
            <value>穿越火线</value>
        </set>
    </property>

    <!--6:Null注入-->
    <property name="wife">
        <null/>
    </property>

    <!--7:properties-->
    <property name="info">
        <props>
            <prop key="学号">20210001</prop>
            <prop key="性别">男</prop>
            <prop key="姓名">张三</prop>
        </props>
    </property>

</bean>
```

测试程序

```java
public static void main(String[] args) {
    ApplicationContext context =
            new ClassPathXmlApplicationContext("beans.xml");
    Student student = (Student) context.getBean("student");
    System.out.println(student);
}
```

输出结果

```java
Student
(
    name=张三, 
    address=Address(address=北京), 
    book=[红楼梦, 西游记, 三国演义, 水浒传], 
    hobbies=[上网, 唱歌, 写作], 
    card={身份证=111111222222223333, 银行卡=2145654654231}, 
    games=[英雄联盟, 绝地求生, 穿越火线], 
    info={姓名=张三, 学号=20210001, 性别=男}, 
    wife=null
)
```

### 6.3、扩展方式注入

![image-20210129170005738](C:\Users\LiuFeiyu\AppData\Roaming\Typora\typora-user-images\image-20210129170005738.png)

不能直接使用，需要导入xml约束

- p命名空间 --> Set属性注入

  ```xml
  xmlns:p="http://www.springframework.org/schema/p"
  ```

  ```xml
  <!--p命名空间注入，可以直接注入属性的值：property-->
  <bean id="user" class="com.neusoft.pojo.User" p:name="p" p:age="18"/>
  ```

  ![image-20210129170546326](C:\Users\LiuFeiyu\AppData\Roaming\Typora\typora-user-images\image-20210129170546326.png)

- c命名空间 --> 构造器注入

  ```xml
  xmlns:c="http://www.springframework.org/schema/c"
  ```

  ```xml
  <!--p命名空间注入，可以直接注入属性的值：property-->
  <bean id="user" class="com.neusoft.pojo.User" p:name="p" p:age="21"/>
  ```

  ![image-20210129165713440](C:\Users\LiuFeiyu\AppData\Roaming\Typora\typora-user-images\image-20210129165713440.png)

### 6.4、bean作用域

在web项目中会使用后4种作用域，正常使用为单例模式和原型模式

| Scope                                                        | Description                                                  |
| :----------------------------------------------------------- | :----------------------------------------------------------- |
| [singleton](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-scopes-singleton) | (Default) Scopes a single bean definition to a single object instance for each Spring IoC container. |
| [prototype](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-scopes-prototype) | Scopes a single bean definition to any number of object instances. |
| [request](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-scopes-request) | Scopes a single bean definition to the lifecycle of a single HTTP request. That is, each HTTP request has its own instance of a bean created off the back of a single bean definition. Only valid in the context of a web-aware Spring `ApplicationContext`. |
| [session](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-scopes-session) | Scopes a single bean definition to the lifecycle of an HTTP `Session`. Only valid in the context of a web-aware Spring `ApplicationContext`. |
| [application](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-scopes-application) | Scopes a single bean definition to the lifecycle of a `ServletContext`. Only valid in the context of a web-aware Spring `ApplicationContext`. |
| [websocket](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#websocket-stomp-websocket-scope) | Scopes a single bean definition to the lifecycle of a `WebSocket`. Only valid in the context of a web-aware Spring `ApplicationContext`. |

spring默认单例模式

![z](C:\Users\LiuFeiyu\AppData\Roaming\Typora\typora-user-images\image-20210129171432307.png)



- 单例模式

  ![singleton](C:\Users\LiuFeiyu\AppData\Roaming\Typora\typora-user-images\singleton.png)

  ```xml
  <bean id="accountService" class="com.hitech.example" scope="singleton"/>
  ```

- 原型模式

  ![prototype](C:\Users\LiuFeiyu\AppData\Roaming\Typora\typora-user-images\prototype.png)

  ```xml
  <bean id="accountService" class="com.hitech.example" scope="prototype"/>
  ```

作用域验证

```java
@Test
public void test2(){
    ApplicationContext context =
        new ClassPathXmlApplicationContext("UserBeans.xml");

    User user = context.getBean("user3",User.class);
    User user2 = context.getBean("user3",User.class);

    System.out.println(user==user2);
    System.out.println(user.hashCode());
    System.out.println(user2.hashCode());
}
```

## 7、Bean的自动装配

### 7.1、简介

- 自动装配是Spring满足bean依赖的一种方式
- Spring会在上下文中自动寻找，并自动给bean装配属性
- 就是自动配置对象

在Spring中有3种装配方式

- 在xml中显式配置
- 在java中显式配置
- **隐式的字段装配bean【重点】**

### 7.2、测试环境

一个人有两个宠物（猫和狗）

![image-20210129175817998](C:\Users\LiuFeiyu\AppData\Roaming\Typora\typora-user-images\image-20210129175817998.png)

```java
public class Dog {
    public void shout(){
        System.out.println("汪~");
    }
}
```

```java
public class Cat {
    public void shout(){
        System.out.println("喵~");
    }
}
```

```java
@Getter
@Setter
@ToString
public class People {
    private String name;
    private Cat cat;
    private Dog dog;
}
```

```xml
<bean id="cat" class="com.neusoft.pojo.Cat"/>
<bean id="dog" class="com.neusoft.pojo.Dog"/>

<bean id="people" class="com.neusoft.pojo.People">
    <property name="name" value="petter"/>
    <property name="cat" ref="cat"/>
    <property name="dog" ref="dog"/>
</bean>
```

```java
@Test
public void test1(){
    ApplicationContext context =
            new ClassPathXmlApplicationContext("beans.xml");
    People people = context.getBean("people",People.class);

    people.getCat().shout();
    people.getDog().shout();
}
```

### 7.3、ByName自动装配

```xml
<!--
    byName：会自动在容器上下文中查找 和自己对象set方法后面的值对应的bean_id，
    区分大小写，检测为小写id，匹配形参名和bean_id
-->

<bean id="people" class="com.neusoft.pojo.People" autowire="byName">
    <property name="name" value="petter"/>
</bean>
```

### 7.4、ByType自动装配

```XML
<!--
    byType：会自动在容器上下文中查找，和自己对象属性类型相同的bean，
-->

<bean id="people1" class="com.neusoft.pojo.People" autowire="byType">
    <property name="name" value="petter"/>
</bean>
```

小结：

- byname的时候，需要保证所有bean的id唯一，并且这个bean需要和自动注入的属性的set方法的值一致
- bytype的时候，需要保证所有bean的clas唯一，并且这个bean需要和自动注入的属性的类型一致

### 7.5、使用注解实现自动装配

参考官方文档1.9

如何使用注解？

- 导入约束

- 配置注解支持

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xmlns:context="http://www.springframework.org/schema/context"
      xsi:schemaLocation="http://www.springframework.org/schema/beans
          https://www.springframework.org/schema/beans/spring-beans.xsd
          http://www.springframework.org/schema/context
          https://www.springframework.org/schema/context/spring-context.xsd">
  
      <context:annotation-config/>
  
  </beans>
  ```

**@Autowired**

![image-20210201104134678](C:\Users\LiuFeiyu\AppData\Roaming\Typora\typora-user-images\image-20210201104134678.png)

直接在属性上使用

```java
@Getter
@Setter
@ToString
public class People {
    private String name;
    @Autowired
    private Cat cat;
    @Autowired
    private Dog dog;
}
```

可以在Set方法使用

在@autowired存在的时候，可以不需要写Setter，前提是自动装配的属性在Spring容器中存在，且名字对应

即为

```xml
<bean id="cat" class="com.neusoft.pojo.Cat"/>
<bean id="dog" class="com.neusoft.pojo.Dog"/>
```

id要等同于实体类的属性名（cat -- cat）

- PS：@Nullable和@Autowired(required = false)

  ![image-20210201105412335](C:\Users\LiuFeiyu\AppData\Roaming\Typora\typora-user-images\image-20210201105412335.png)

  @Nullable作用为：允许属性为空，可以避免报空指针异常
  而@Autowired(required = false)显示定义则说明这个对象可以为空，否则不允许为空

- PS：@Qualifier(value = "cat111")
  如果自动装配环境比较复杂，无法通过一个Autowired注解完成，可以配合@Qualifier使用。
  在多个bean存在的时候，指定一个唯一的bean，匹配bean的id

**@Resource**

```java
public class People {
    private String name;
    @Resource(naem = "cat22")
    private Cat cat;
    @Resource
    private Dog dog;
}
```

@Resource 和 @Autowired 的区别

- 都是用来自动装配的，都可以放在属性字段上
- @Autowired通过byType的方式实现,而且必须要求这个对象存在！【常用】
- @Resource**默认**通过byName的方式实现，如果找不到名字，则通过 byType实现！如果两个都找不到的情况下，就报错！【常用】
- 执行顺序不同：@Autowired通过byType的方式实现。@Resource默认通过byName的方式实现

## 8、Spring使用注解开发

在Spring4之后，要使用注解开发，要导入AOP【目前在gradle中，webmvc下包含了aop，无需手动导入】

![image-20210201113207475](C:\Users\LiuFeiyu\AppData\Roaming\Typora\typora-user-images\image-20210201113207475.png)

使用注解开发需要在**Spring配置文件**中导入context约束，增加注解支持。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>

</beans>
```

### 8.1、bean

在注解开发中，bean基本不会被使用到，因为注解已经帮你完成了大部分工作

### 8.2、属性如何注入

```java
// 该注解等价于 <bean id="user" class="com.neusoft.pojo.User"/>
// 由于未在Spring配置文件中设定名字，默认名字为user
@Component
public class User {
    // 等价于 <property name="name" value="AFF">
    @Value("AFF")
    public String name;

    // 也可以在set方法上注入，优先使用set方法
    @Value("AF12")
    public void setName(String name) {
        this.name = name;
    }
}
```

### 8.3、Component衍生的注解

在web开发中，会按照mvc三层架构分层

```java
@Controller
public class UserController {
}
```

```java
@Repository
public class UserDao {
}
```

```java
@Service
public class UserService {
}
```

这几个注解功能是一样的，这代表将某个类注册到Spring中，自动装配Bean

PS：在配置了所有组件注解后，在**Spring配置文件**中可以直接编写自动扫描所有包，自动生效包下的所有注解

```xml
<context:component-scan base-package="com.neusoft"/>
```

### 8.4、自动装配设置

- @Autowired 通过类型，名字自动装配

  如果Autowired不能唯一自动装配上属性，需要和@Qualifier(value="xxx")连用

- @Nullable    标记一个字段，说明该字段可以为空

- @Resource 通过名字，类型自动装配

### 8.5、作用域

```java
@Scope("singleton")
@Scope("prototype")
public class User{}
```

### 8.6、小结

xml和注解

- xml：更加万能，适用于任何场合，维护简单方便（只需要在一个文件中修改）
- 注解：不是自己的类无法使用，维护相对复杂（ 很多文件都要改）

xml和注解的使用场合

- xml管理bean
- 注解负责完成属性的注入（value）

注意，必须让注解生效，需要开启注解的支持

## 9、使用Java的方式配置Spring

脱离Spring的xml配置，将配置交给java来做

JavaConfig

module7

### 9.1、配置实践

User.java

```java
@Getter
@Setter
@ToString
@Component // 这个注解的意思是，说明这个类被被Spring接管了，注册到容器中
public class User {
    @Value("java") // 属性注入值
    private String name;
}
```

AfConfig.java 配置文件（配置类）

```java
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
```

测试方法

```java
//如果完全使用了配置类方式去做，我们就只能通过AnnotationConfig上下文来获取容器，通过配置class对象加载
@Test
public void test(){
    ApplicationContext context =
            new AnnotationConfigApplicationContext(AfConfig.class);
    User user = (User) context.getBean("getUser"); // name和方法名相同
    System.out.println(user);
}
```

## 10、代理模式

**代理模式是SpringAOP的底层**，【面试重点：SpringAOP，SpringMVC】

![image-20210201145623270](C:\Users\LiuFeiyu\AppData\Roaming\Typora\typora-user-images\image-20210201145623270.png)

### 10.1、静态代理

接口

```java
// 租房
public interface Rent {
    void rent();
}
```

真实角色

```java
// 房东
public class Host implements Rent {
    @Override
    public void rent() {
        System.out.println("房东要出租房子");
    }
}
```

代理角色

```java
@AllArgsConstructor
@NoArgsConstructor
public class Proxy implements Rent {
    private Host host;
    
    @Override
    public void rent() {
        host.rent();
        seeHouse();
        fare();
        contract();
    }
    // 看房
    public void seeHouse(){
        System.out.println("中介带你看房");
    }
    // 收中介费
    public void fare(){
        System.out.println("收中介费");
    }
    // 合同
    public void contract(){
        System.out.println("签合同");
    }
}
```

客户端访问代理角色

```java
public class Client {
    public static void main(String[] args) {
        // 房东要出租房子
        Host host = new Host();
        // 代理，中介帮房东租房子，但是中介会有一些附属操作
        Proxy proxy = new Proxy(host);
        // 你不用面对房东，直接找中介租房
        proxy.rent();
    }
}
```

角色分析

- 抽象角色：一般会使用接口或者抽象类来解决
- 真实角色：被代理的角色
- 代理角色：代理真实角色 -- 代理真实角色后，我们一般会做一些附属操作
- 客户：访问代理对象的人

代理模式优点：

- 可以使真实角色操作更加纯粹，不用去关注一些公共给业务
- 公共也就交给代理角色，实现了业务的分工
- 公共业务发生扩展的时候，方便集中管理

代理模式缺点

- 一个真实角色就会产生一个代理角色；代码量会翻倍，开发效率变低

### 10.2、AOP解析

![image-20210201160205571](C:\Users\LiuFeiyu\AppData\Roaming\Typora\typora-user-images\image-20210201160205571.png)

UserService接口

```java
public interface UserService {
    void add();
    void delete();
    void update();
    void query();
}
```

接口实现类UserServiceImpl

```java
public class UserServiceImpl implements UserService{
    @Override
    public void add() {
        System.out.println("增加一个用户");
    }

    @Override
    public void delete() {
        System.out.println("删除一个用户");
    }

    @Override
    public void update() {
        System.out.println("修改一个用户");
    }

    @Override
    public void query() {
        System.out.println("查询一个用户");
    }
}
```

代理类

```java
public class UserServiceProxy implements UserService{

    UserServiceImpl userService;

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public void add() {
        log("add");
        userService.add();
    }

    @Override
    public void delete() {
        log("delete");
        userService.delete();
    }

    @Override
    public void update() {
        log("update");
        userService.update();
    }

    @Override
    public void query() {
        log("query");
        userService.query();
    }

    // 日志方法
    public void log(String msg){
        System.out.println("[MSG]使用了"+msg+"方法");
    }

}
```

客户端

```java
public class Client {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        UserServiceProxy proxy = new UserServiceProxy();
        proxy.setUserService(userService);
        proxy.delete();
    }
}
```

### 10.3、动态代理

**参见代码08-proxy**

- 动态代理和静态代理角色一样
- 动态代理的代理类是动态生成的，不是我们直接写好的
- 动态代理分为两大类：基于接口的动态代理，基于类的动态代理
  - 基于接口 -- JDK动态代理
  - 基于类：cglib
  - java字节码：JAVAssist

需要了解两个类：Proxy，InvocationHandler 

动态代理的好处

- 可以使真实角色的操作更加纯粹，不用去关注一些公共的业务
- 公共也就就交给代理角色，实现了业务的分工
- 公共业务发生扩展的时候,方便集中管理
- 一个动态代理类代理的是一个接口，一般就是对应的一类业务

- 一个动态代理类可以代理多个类，只要是实现了同一个接口即可

总结出工具类**ProxyInvocationHandler**

```java
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// 自动生成代理类
public class ProxyInvocationHandler implements InvocationHandler {

    // 被代理的接口
    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    // 生成得到的代理类
    public Object getProxy(){
        return Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);
    }

    //处理代理实例，并返回结果
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) 
            throws Throwable {
        // 动态代理的本质，就是使用反射机制实现
        log(method.getName());
        return method.invoke(target, args);
    }

    public void log(String msg){
        System.out.println("执行了"+msg+"()方法");
    }
}
```

## 11、AOP

### 11.1、什么是AOP

AOP（Aspect Oriented Programming）意为：**面向切面编程**，通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术，AOP是OOP的延续，是软件开发中的一个热点，也是Spring框架中的一个重要内容，是函数式编程的一种行生范型。利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低,提高程序的可重用性，同时提高了开发的效率。

![image-20210202101719453](C:\Users\LiuFeiyu\AppData\Roaming\Typora\typora-user-images\image-20210202101719453.png)

### 11.2、AOP在Spring中的应用

==提供声明式事务；允许用户自定义切面==

**假设我们要加一个功能 -- 日志LOG**

- 横切关注点：跨越应用程序多个模块的方法或功能。即是，与我们业务逻辑无关的，但是我们需要关注的部分，就是横切关注点。如日志,安全,缓存,事务等等…【**就是我们的日志功能】**
- 切面（ASPECT）：横切关注点，被模块化的特殊对象。即,它是一个类。**【Log日志类】**
- 通知（Advice）：切面必须要完成的工作。即，它是类中的一个方法。**【Log类中的一个方法】**
- 目标（Target）：被通知的目标对象。【就是一个外部接口或者一个方法】
- 代理（Proxy）：向目标对象应用通知之后创建的对象。【生成的代理类】
- 切入点（Pointcut）：切面通知 执行的“地点“的定义。【invoke】
- 连接点（Joinpoint）：与切入点匹配的执行点。【Methor】

![image-20210202103157266](C:\Users\LiuFeiyu\AppData\Roaming\Typora\typora-user-images\image-20210202103157266.png)

SpringAOP中，通过Advice定义横切逻辑，Spring中支持5种类型的Advice：

|   通知类型   |        连接点        |                    实现接口                     |
| :----------: | :------------------: | :---------------------------------------------: |
|   前置通知   |        方法前        |   org.springframework.aop.MethorBeforeAdvice    |
|   后置通知   |        方法后        |  org.Springframework.aop.AfterReturningAdvice   |
|   环绕通知   |       方法前后       |   org.aopalliance.intercept.MethodInterceptor   |
| 异常抛出通知 |     方法抛出异常     |      org.springframework.aop.ThrowsAdvice       |
|   引介通知   | 类中增加新的方法属性 | org.springframework.aop.IntroductionInterceptor |

即AOP在**不改变原有代码的**情况下，增加新的功能

### 11.3、使用Spring实现AOP

需要导入依赖包

```java
implementation group: 'org.aspectj', name: 'aspectjweaver', version: '1.9.6'
```

#### 11.3.1、方式1：使用Spring的API接口

项目结构

![image-20210202113911968](C:\Users\LiuFeiyu\AppData\Roaming\Typora\typora-user-images\image-20210202113911968.png)

- Log包

AfterLog.java

```java
public class AfterLog implements AfterReturningAdvice {

    // returnValue  返回值
    // method       要执行的目标对象的方法
    // args         参数
    // target       目标对象
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target)
            throws Throwable {
        System.out.println(
                "执行了"+method.getName()
                +"()方法,返回结果为："
                +returnValue);
    }
}
```

BeforeLog.java

```java
public class BeforeLog implements MethodBeforeAdvice {

    // method 要执行的目标对象的方法
    // args   参数
    // target 目标对象
    @Override
    public void before(Method method, Object[] args, Object target)
            throws Throwable {
        if (target != null) {
            System.out.println(
                    target.getClass().getName()
                    +"的"+method.getName()
                    +"()方法被执行了");
        }
    }
}
```

- service包

UserService接口

```java
public interface UserService {
    void add();
    void delete();
    void update();
    void query();
}
```

UserServiceImpl接口实现

```java
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
```

资源文件：**Spring配置文件**

![image-20210202115001673](C:\Users\LiuFeiyu\AppData\Roaming\Typora\typora-user-images\image-20210202115001673.png)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd
        ">

    <bean id="userService" class="com.neusoft.service.UserServiceImpl"/>
    <bean id="beforeLog" class="com.neusoft.log.BeforeLog"/>
    <bean id="afterLog" class="com.neusoft.log.AfterLog"/>

    <!--方式1，使用原生Spring API接口-->
    <!--配置AOP：导入AOP约束-->
    <aop:config>
        <!--切入点-->
        <!--expression:表达式，execution（要执行的位置 *  *  *  *  *）-->
        <aop:pointcut id="pointcut" expression="execution(* com.neusoft.service.UserServiceImpl.*(..))"/>

        <!--执行环绕增加-->
        <aop:advisor advice-ref="beforeLog" pointcut-ref="pointcut"/>
        <aop:advisor advice-ref="afterLog" pointcut-ref="pointcut"/>
    </aop:config>

</beans>
```

测试类

```java
public static void main(String[] args) {
    ApplicationContext context =
            new ClassPathXmlApplicationContext("applicationContext.xml");
    // 动态代理代理的是接口，不是接口实现类
    UserService userService = (UserService) context.getBean("userService");
    userService.add();

}
```

#### 11.3.2、方式2：使用自定义类来实现

新建自定义类DiyPointCut

```java
public class DiyPointCut {
    public void before(){
        System.out.println("========方法执行前========");
    }
    public void after(){
        System.out.println("========方法执行后========");
    }
}
```

其余包下的文件文件不需要修改，修改Spring配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd
        ">

    <bean id="userService" class="com.neusoft.service.UserServiceImpl"/>
    <bean id="beforeLog" class="com.neusoft.log.BeforeLog"/>
    <bean id="afterLog" class="com.neusoft.log.AfterLog"/>

    <!--方式2：自定义类-->
    <bean id="diy" class="com.neusoft.diy.DiyPointCut"/>

    <aop:config>
        <!--自定义切面  ref 要引用的类-->
        <aop:aspect ref="diy">
            <!--切入点-->
            <aop:pointcut id="point" expression="execution(* com.neusoft.service.UserServiceImpl.*(..))"/>
            <!--通知-->
            <aop:before method="before" pointcut-ref="point"/>
            <aop:after method="after" pointcut-ref="point"/>
        </aop:aspect>
    </aop:config>
</beans>
```

#### 11.3.3、方式3：使用注解实现

新建类AnnotationPointCut

```java
//使用注解方式实现
@Aspect //标注这个类是一个切面
public class AnnotationPointCut {

    @Before("execution(* com.neusoft.service.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("--------方法执行前--------");
    }

    @After("execution(* com.neusoft.service.UserServiceImpl.*(..))")
    public void after(){
        System.out.println("--------方法执行后--------");
    }

    // 在环绕增强中，我们可以给定一个参数，代表我们要获取切入的点
    @Around("execution(* com.neusoft.service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint joinPoint)
            throws Throwable {
        System.out.println("环绕前");
        Signature signature = joinPoint.getSignature();// 获得签名
        System.out.println("signature："+ signature);
        Object proceed = joinPoint.proceed();
        System.out.println("环绕后");        // 执行方法
        System.out.println("proceed:"+proceed);
    }
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd
        ">

    <bean id="userService" class="com.neusoft.service.UserServiceImpl"/>
    <bean id="beforeLog" class="com.neusoft.log.BeforeLog"/>
    <bean id="afterLog" class="com.neusoft.log.AfterLog"/>

    <!--方式3-->
    <bean id="annotationPointCut" class="com.neusoft.anno.AnnotationPointCut"/>
    <!--开启注解支持-->
    <aop:aspectj-autoproxy/>

</beans>
```

执行结果

![image-20210202141835421](C:\Users\LiuFeiyu\AppData\Roaming\Typora\typora-user-images\image-20210202141835421.png)

## 12、Spring整合Mybatis

步骤：

- 导入相关的jar包

  - junit【gradle附带，可以不需要导入】
  - mybatis
  - mysql
  - spring相关
  - aop植入
  - mybatis-spring

  ```java
  implementation group: 'org.mybatis', name: 'mybatis', version: '3.5.6'
  implementation group: 'mysql', name: 'mysql-connector-java', version: '5.1.49'
  implementation group: 'org.springframework', name: 'spring-jdbc', version: '5.3.3'
  implementation group: 'org.aspectj', name: 'aspectjweaver', version: '1.9.6'
  implementation group: 'org.mybatis', name: 'mybatis-spring', version: '2.0.6'
  ```

- 编写配置文件
- 测试

### 12.1、Mybatis测试

- 编写实体类User

  ```java
  @Data
  public class User {
      private int id;
      private String name;
      private String pwd;
  }
  ```

- 编写**核心配置文件**

- 编写接口

  ```java
  public interface UserMapper {
      List<User> selectAllUser();
  }
  ```

- 编写Mapper.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE mapper
          PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <mapper namespace="com.neusoft.dao.UserMapper">
      <select id="selectAllUser" resultType="User">
          select * from mybatis.user;
      </select>
  </mapper>
  ```

- 测试方法

  ```java
  @Test
  public void testMybatis(){
      SqlSession sqlSession = MybatisUtils.getSqlSession();
      UserMapper mapper = sqlSession.getMapper(UserMapper.class);
      List<User> userList = mapper.selectAllUser();
      for (User user : userList) {
          System.out.println(user);
      }
      sqlSession.close();
  }
  ```

### 12.2、Mybatis-Spring

官方中文文档：http://mybatis.org/spring/zh/index.html

- 编写数据源
- 编写SqlSessionFactory
- SqlSessionTemplate
- 需要给接口加实现类【多出的一步】
- 测试：将自己写的实现类注入到Spring中，测试使用

**PS：**

**Spring整合Mybatis后，多了一个接口实现类【UserMapperImpl.java】**

**因为Spring要去接管这个对象，Spring需要自动创建，但是mybatis里的东西Spring无法自动创建，只能在==接口实现==里手动写一个set方法，而这个set方法是做原来mybatis做的事情，将其变成一个业务类来做，就形成了接口实现类**

**原先，接口的实现是放在业务层做的，现在Spring整合之后，就可以直接在Spring里面定义了，在这个实现类中去操作原来mybatis做的事情就可以了**

#### 12.2.1、操作流程

- spring-dao.xml -- spring整合mybatis配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--DataSource数据源：使用Spring的数据源替换Mybatis的配置 c3p0 dbcp druid
        使用Spring提供的JDBC
    -->
    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=false&amp;useUnicode=true&amp;characterEncoding=UTF-8&amp;serverTimezone=UTC&amp;allowPublicKeyRetrieval=true"/>
        <property name="username" value="root"/>
        <property name="password" value="root"/>
    </bean>

    <!--sqlSessionFactory-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <!--绑定mybatis配置文件-->
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <property name="mapperLocations" value="classpath*:com/neusoft/dao/*.xml" />
    </bean>

    <!--SqlSessionTemplate：就是我们使用的sqlSession-->
    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
        <!--只能使用构造器注入SqlSessionFactory-->
        <constructor-arg index="0" ref="sqlSessionFactory"/>
    </bean>
</beans>
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <import resource="spring-dao.xml"/>

    <bean id="userMapper" class="com.neusoft.dao.UserMapperImpl">
        <property name="sqlSession" ref="sqlSession"/>
    </bean>

</beans>
```

- mybatis-config.xml 核心配置文件修改，仅仅保留一些配置，其他事情交给Spring【spring-dao.xml】去实现

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <settings>
        <setting name="mapUnderscoreToCamelCase" value="true"/>
    </settings>

    <typeAliases>
        <package name="com.neusoft.pojo"/>
    </typeAliases>

</configuration>
```

- 接口不做变化，增加步骤，实现UserMapper接口 -- 【UserMapperImpl.java】

```java
public class UserMapperImpl implements UserMapper{

    // 我们的所有操作，原先都使用SqlSession来执行，现在都使用SqlSessionTemplate来执行
    private SqlSessionTemplate sqlSession;

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<User> selectAllUser() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.selectAllUser();
    }
}
```

- 测试方法 -- 通过获取context来getBean，即为以前的sqlSession().getMapper(UserMapper.class)

```java
@Test
public void testSpringMybatis(){
    ApplicationContext context = 
        new ClassPathXmlApplicationContext("applicationContext.xml");
    UserMapper mapper = context.getBean("userMapper", UserMapper.class);
    List<User> userList = mapper.selectAllUser();
    for (User user : userList) {
        System.out.println(user);
    }
}
```

- 输出结果

![image-20210203101835910](C:\Users\LiuFeiyu\AppData\Roaming\Typora\typora-user-images\image-20210203101835910.png)

#### 12.2.2、核心代码

- 1、Spring中的数据源

```xml
<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
    <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
    <property name="url" value="jdbc:mysql://localhost:3306/mybatis"/>
    <property name="username" value="root"/>
    <property name="password" value="root"/>
</bean>
```

- 2、Spring中的sqlSessionFactory -- 等价于以前获取核心配置文件流，在核心配置文件中绑定Mapper的过程，对比MybatisUtils类

```java
public class MybatisUtils {
    private static SqlSessionFactory sqlSessionFactory;
    static {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession(true);
    }
}
```

```xml
<!--sqlSessionFactory-->
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource"/>
    <!--绑定mybatis配置文件-->
    <property name="configLocation" value="classpath:mybatis-config.xml"/>
    <property name="mapperLocations" value="classpath*:com/neusoft/dao/*.xml" />
</bean>
```

- 3、SqlSessionTemplate -- 对应为MybatisUtils中的getSqlSqssion方法，开启sqlSession

```xml
<!--SqlSessionTemplate：就是我们使用的sqlSession-->
<bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
    <!--只能使用构造器注入SqlSessionFactory-->
    <constructor-arg index="0" ref="sqlSessionFactory"/>
</bean>
```

- 4、获取mapper，需要的class是接口实现类

```xml
<bean id="userMapper" class="com.neusoft.dao.UserMapperImpl">
    <property name="sqlSession" ref="sqlSession"/>
</bean>
```

#### 12.2.3、bug处理

*通配符无法使用，会抛出异常

![image-20210202165427674](C:\Users\LiuFeiyu\AppData\Roaming\Typora\typora-user-images\image-20210202165427674.png)

[警告]mapperLocations无法找到匹配的文件，[报错]Mapper未注册

![image-20210202165331811](C:\Users\LiuFeiyu\AppData\Roaming\Typora\typora-user-images\image-20210202165331811.png)

通过修改，在classpath后加入一个星号*，即可解决问题，推测是由于spring-jdbc版本过高，不支持通配符所导致。

#### 12.2.4、操作流程简化

参见官方文档：**使用 SqlSession** -- **SqlSessionDaoSupport**

```java
public class UserMapperImpl2 extends SqlSessionDaoSupport implements UserMapper{
    @Override
    public List<User> selectAllUser() {
        return getSqlSession().getMapper(UserMapper.class).selectAllUser();
    }
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">
    <import resource="spring-dao.xml"/>
    <bean id="userMapper2" class="com.neusoft.dao.UserMapperImpl2">
        <property name="sqlSessionFactory" ref="sqlSessionFactory"/>
    </bean>
</beans>
```

## 13、声明式事务

### 13.1、事务（TRANSACTION）

**基本概念**

数据库专有名词，将多条sql合成一组进行操作

事务流程

| 顺序 | 关键词              | 含义             |
| ---- | ------------------- | ---------------- |
| 1    | SET autocommit = 0; | 关闭事务自动提交 |
| 2    | START TRANSACTION;  | 开启一个事务     |
| 3    | SQL语句;            | 执行sql语句      |
| 4.1  | COMMIT;             | 提交事务         |
| 4.2  | ROLLBACK;           | 回滚             |
| 5    | SET autocommit = 1; | 开启事务自动提交 |

事务的特性

- 把一组（多条sql）业务当成一个业务来做，**要么都成功，要么都失败**
- 事务在项目开发中十分重要，涉及到数据的一致性问题
- 确保完整性和一致性

**事务的ACID原则**

https://blog.csdn.net/dengjili/article/details/82468576

- **原子性（Atomicity）**
  事务中的操作要么都成功（COMMIT），要么都失败（ROLLBACK）。

- **一致性（Consistency）**

  事务前后数据的完整性必须保持一致。

- **隔离性（Isolation）**

  数据库为每一个用户开启的事务，不能被其他事务的操作数据所干扰，多个并发事务之间要相互隔离。防止数据损坏

- **持久性（Durability）**

  一个事务一旦被提交（COMMIT），它对存储器（不一定是数据库）中数据的改变就是永久性的，接下来即使存储器发生故障也不应该对数据结果有任何影响

**事务的隔离级别种类**

- 脏读：

  指一个事务读取了另外一个事务未提交的数据。

  例如A和B互相转账200；C和D互相转账300。如果AB执行过程中读取了CD未提交的事务的数据，就是脏读

- 不可重复读：

  在一个事务内读取表中的某一行数据，多次读取结果不同。（这个不一定是错误，只是某些场合不对）

  例如在查询的时候【A：100，B：200，C：**300**】导出报表的时候成了【A：100，B：200，C：==999==】

- 虚读(幻读)：

  在一个事务内读取到了别的事务插入的数据，导致前后读取不一致。

  查询的时候【A：100，B：200，C：300】导出报表的时候成了【A：100，B：200，C：999，==D：100==】多了一行数据

### 13.2、Spring中的事务管理

- 声明式事务：AOP【常用】
- 编程式事务：需要在代码中，进行事务的管理【不常用】

**事务的实现**

- 增加接口方法并实现

```java
public interface UserMapper {
    List<User> selectAllUser();

    int addUser(User user);
    int deleteUserById(@Param("id") int id);
}
```

```java
public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper{

    @Override
    public List<User> selectAllUser() {

        User user = new User(7, "小王", "159");

        UserMapper mapper = getSqlSession().getMapper(UserMapper.class);

        mapper.addUser(user);
        mapper.deleteUserById(8);

        return mapper.selectAllUser();
    }

    @Override
    public int addUser(User user) {
        return getSqlSession().getMapper(UserMapper.class).addUser(user);
    }

    @Override
    public int deleteUserById(int id) {
        return getSqlSession().getMapper(UserMapper.class).deleteUserById(id);
    }
}
```

Mapper.xml中，编写错误的语句，进行事务的测试

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.neusoft.dao.UserMapper">

    <select id="selectAllUser" resultType="User">
        select * from mybatis.user;
    </select>

    <insert id="addUser" parameterType="user">
        insert into mybatis.user (id, name, pwd) VALUES (#{id}, #{name}, #{pwd});
    </insert>

    <delete id="deleteUserById" parameterType="int">
        <!--错误的sql语句-->
        deletes from mybatis.user where id=#{id};
    </delete>

</mapper>
```

spring-dao配置文件中配置事务

```xml
<!--配置声明式事务-->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <!--<constructor-arg ref="dataSource" />-->
    <property name="dataSource" ref="dataSource"/>
</bean>

<!--结合AOP实现事务的植入-->
<!--配置事务的类-->
<tx:advice id="txAdvice" transaction-manager="transactionManager">
    <!--给哪些方法配置事务-->
    <!--配置事务的传播特性-->
    <tx:attributes>
        <tx:method name="add" propagation="REQUIRED"/>
        <tx:method name="delete" propagation="REQUIRED"/>
        <tx:method name="update" propagation="REQUIRED"/>
        <tx:method name="query" read-only="true"/>
        <tx:method name="*" propagation="REQUIRED"/>
    </tx:attributes>
</tx:advice>

<!--配置事务切入-->
<aop:config>
    <aop:pointcut id="txPointCut" expression="execution(* com.neusoft.dao.*.*(..))"/>
    <aop:advisor advice-ref="txAdvice" pointcut-ref="txPointCut"/>
</aop:config>
```

添加事务前的执行结果

![image-20210203145929563](C:\Users\LiuFeiyu\AppData\Roaming\Typora\typora-user-images\image-20210203145929563.png)

添加事务后的执行结果

![image-20210203150038423](C:\Users\LiuFeiyu\AppData\Roaming\Typora\typora-user-images\image-20210203150038423.png)

【小王】并没有被添加进去，说明事务执行成功了

### 13.3、事务总结

为什么需要事务

- 如果不配置事务，可能会存在数据提交不一致的情况
- 如果我们不在Spring中去配置声明式事务，就需要在代码中手动配置事务
- 事务在项目开发中十分重要，涉及到数据的一致性和完整性问题