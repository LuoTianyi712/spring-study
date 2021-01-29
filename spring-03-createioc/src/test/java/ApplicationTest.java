import com.neusoft.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationTest {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("ApplicationContext.xml");

        User user = (User) context.getBean("user");
        user.show();

        User user2 = (User) context.getBean("user");
        System.out.println(user==user2);
    }
}
