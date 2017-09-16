package cn.iselab.inventory.site.dao;

import cn.iselab.inventory.site.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by ROGK on 2017/9/14.
 */
@Transactional
public interface UserDao extends CrudRepository<User, Long>{

    User findByEmail(String email);

    User findBymobile(String mobile);
}
