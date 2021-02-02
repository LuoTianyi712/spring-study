import com.neusoft.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplicationTest {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        // 动态代理代理的是接口，不是接口实现类
        // 抛出异常
        // Exception in thread "main" java.lang.ClassCastException:
        // class com.sun.proxy.$Proxy5 cannot be cast to class com.neusoft.service.UserServiceImpl
        // (com.sun.proxy.$Proxy5 and com.neusoft.service.UserServiceImpl are in unnamed module of loader 'app')

        UserService userService = (UserService) context.getBean("userService");
        userService.add();

    }
}
