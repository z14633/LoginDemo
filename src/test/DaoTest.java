package test;

import cn.h3c.user.dao.UserDao;
import cn.h3c.user.domain.User;
import org.junit.Test;

public class DaoTest {
    @Test
    public void testFindByUsername(){
        String name = "赵柳";
        UserDao userDao = new UserDao();
        User user = userDao.findByUsername(name);
        System.out.println(user);
    }
    @Test
    public void testAdd(){
        UserDao userDao = new UserDao();
        User user = new User();
        user.setUsername("李四");
        user.setPassword("123");
        userDao.add(user);

    }
}
