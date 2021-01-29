import com.neusoft.pojo.User;
import com.neusoft.pojo.UserTwo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationTest {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("ApplicationContext.xml");

        UserTwo user = (UserTwo) context.getBean("u2");
        user.show();

    }
}
