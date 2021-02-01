import com.neusoft.config.AfConfig;
import com.neusoft.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringApplicationTest {

    //如果完全使用了配置类方式去做，我们就只能通过AnnotationConfig上下文来获取容器，通过配置class对象加载
    @Test
    public void test(){
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AfConfig.class);
        User user = (User) context.getBean("getUser"); // name和方法名相同
        System.out.println(user);
    }
}
