package cn.iselab.inventory.site.service;

import cn.iselab.inventory.site.model.Role;
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

    List<User> getUsers(String keyword);

    List<User2Role> getRoles(long userId);

    void updateUser(User user);

    Role findRole(long id);

    User getUser(long id);

    void deleteUser(User user);

    void updateRole(User2Role role);
}
