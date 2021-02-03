import com.neusoft.dao.UserMapper;
import com.neusoft.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SpringApplicationTest {

//    @Test
//    public void testMybatis(){
//        SqlSession sqlSession = MybatisUtils.getSqlSession();
//
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        List<User> userList = mapper.selectAllUser();
//        for (User user : userList) {
//            System.out.println(user);
//        }
//
//        sqlSession.close();
//    }

    @Test
    public void testSpringMybatis(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper mapper = context.getBean("userMapper2", UserMapper.class);
        List<User> userList = mapper.selectAllUser();
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
