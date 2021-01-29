import com.neusoft.pojo.HelloWorld;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationTest {
    public static void main(String[] args) {
        // 获取Spring上下文对象
        ApplicationContext context =
                new ClassPathXmlApplicationContext("ApplicationContext.xml");
        // 我们的对象现在都在Spring中管理了，要使用，直接去里面取出来就可以
        HelloWorld hello = (HelloWorld) context.getBean("hello");
        System.out.println(hello);
    }
}
