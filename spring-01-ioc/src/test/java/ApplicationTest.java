import com.neusoft.dao.UserDaoImpl;
import com.neusoft.dao.UserDaoMysqlImpl;
import com.neusoft.dao.UserDaoOracleImpl;
import com.neusoft.service.UserServiceImpl;

public class ApplicationTest {
    public static void main(String[] args) {

        //用户实际调用的是业务层（Service） dao层不需要接触
        UserServiceImpl userService = new UserServiceImpl();

        // 创建DaoImpl实现类的对象
        UserDaoMysqlImpl userDaoMysql = new UserDaoMysqlImpl();
        UserDaoImpl userDao = new UserDaoImpl();
        UserDaoOracleImpl userDaoOracle = new UserDaoOracleImpl();

        // 调用set方法，再调用getUser（接收过来的UserDaoMysqlImpl对象的方法）
        userService.setUserDao(userDaoMysql);
        userService.setUserDao(userDaoOracle);
        userService.getUser();
    }
}
