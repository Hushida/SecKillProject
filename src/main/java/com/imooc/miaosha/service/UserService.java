package com.imooc.miaosha.service;

import com.imooc.miaosha.dao.UserDao;
import com.imooc.miaosha.domin.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hushida on 18-4-15.
 */
@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public User getById(int id) {
        return userDao.getById(id);
    }

    //@Transactional   //事务的作用是保证整个事务的完整性，两个操作都成功
    //或者都失败，不允许一个成功一个失败
    public boolean tx() {
        User u1 = new User();
        u1.setId(2);
        u1.setName("2222");
        userDao.insert(u1);

        User u2 = new User();
        u1.setId(1);
        u1.setName("11111");
        userDao.insert(u2);

        return true;
    }
}