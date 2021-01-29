import com.neusoft.pojo.People;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplicationTest {
    @Test
    public void test1(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("beans.xml");
        People people = context.getBean("people1",People.class);

        people.getCat().shout();
        people.getDog().shout();
    }
}
