package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.dao.User2RoleDao;
import cn.iselab.inventory.site.dao.UserDao;
import cn.iselab.inventory.site.model.User;
import cn.iselab.inventory.site.model.User2Role;
import cn.iselab.inventory.site.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ROGK on 2017/9/14.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private User2RoleDao user2RoleDao;

    @Override
    public User createUser(User user){
        return userDao.save(user);
    }

    @Override
    public User getUserByEmail(String email){
        return userDao.findByEmail(email);
    }

    @Override
    public User getUserByMobile(String mobile){
        return userDao.findBymobile(mobile);
    }

    @Override
    public User2Role createRole(User2Role user2Role){
        return user2RoleDao.save(user2Role);
    }

    @Override
    public List<User2Role> getRoles(long userId){
        return user2RoleDao.findByUserId(userId);
    }

    @Override
    public void updateUser(User user){
        userDao.save(user);
    }
}
