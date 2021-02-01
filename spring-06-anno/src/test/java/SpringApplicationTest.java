import com.neusoft.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplicationTest {

    @Test
    public void test1(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        User user = (User) context.getBean("user");

        System.out.println(user.name);
    }
}
