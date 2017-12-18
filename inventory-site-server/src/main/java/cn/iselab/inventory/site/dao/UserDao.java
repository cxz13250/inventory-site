package cn.iselab.inventory.site.dao;

import cn.iselab.inventory.site.model.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by ROGK on 2017/9/14.
 */
@Transactional
public interface UserDao extends CrudRepository<User, Long>,JpaSpecificationExecutor<User>{

    @Query("select u from User u where u.name like concat('%',:keyword,'%') ")
    List<User> findAllUser(@Param("keyword") String keyword);

    User findByEmail(String email);

    User findBymobile(String mobile);
}
