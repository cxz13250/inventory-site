package cn.iselab.inventory.site.dao;

import cn.iselab.inventory.site.model.User2Role;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by ROGK on 2017/9/15.
 */
@Transactional
public interface User2RoleDao extends CrudRepository<User2Role, Long>{
}
