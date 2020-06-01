package cn.h3c.user.service;

import cn.h3c.user.dao.UserDao;
import cn.h3c.user.domain.User;

public class UserService {
    private UserDao userDao = new UserDao();

    /*
    注册功能
     */
    public void regist(User user) throws UserException{
        /**
         * 1,判断user名是否已被注册
         * 2，已经被注册之后则报错UserException
         * 3，如果没有被注册则正常添加
         */
        String username = user.getUsername();
        User userObj = userDao.findByUsername(username);
        if(userObj != null){
            // 说明已经存在需要抛出异常
            throw new UserException("用户名"+ username+"已被注册");
        } else {
            userDao.add(user);
        }
    }
}
