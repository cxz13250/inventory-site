package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.dao.UserDao;
import cn.iselab.inventory.site.model.User;
import cn.iselab.inventory.site.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ROGK on 2017/9/14.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    public User createUser(User user){
        return userDao.save(user);
    }

    public User getUserByEmail(String email){
        return userDao.findByEmail(email);
    }

    public User getUserByMobile(String mobile){
        return userDao.findBymobile(mobile);
    }
}
