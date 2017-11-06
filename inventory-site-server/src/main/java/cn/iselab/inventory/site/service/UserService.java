package cn.iselab.inventory.site.service;

import cn.iselab.inventory.site.model.User;
import cn.iselab.inventory.site.model.User2Role;

import java.util.List;

/**
 * Created by ROGK on 2017/9/14.
 */
public interface UserService {

    User createUser(User user);

    User getUserByEmail(String email);

    User getUserByMobile(String mobile);

    User2Role createRole(User2Role user2Role);

    List<User2Role> getRoles(long userId);

    void updateUser(User user);
}
