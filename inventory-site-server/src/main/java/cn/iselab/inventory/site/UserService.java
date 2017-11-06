package cn.iselab.inventory.site;

import cn.iselab.inventory.site.model.User;

/**
 * Created by ROGK on 2017/9/14.
 */
public interface UserService {

    User createUser(User user);

    User getUserByEmail(String email);

    User getUserByMobile(String mobile);
}
