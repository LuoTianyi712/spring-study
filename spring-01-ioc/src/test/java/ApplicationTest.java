import com.neusoft.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
