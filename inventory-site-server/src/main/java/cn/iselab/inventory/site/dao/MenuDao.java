package cn.iselab.inventory.site.dao;

import cn.iselab.inventory.site.model.Menu;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午8:13 2017/12/4
 * @Modified By:
 */
@Transactional
public interface MenuDao extends CrudRepository<Menu,Long> {

    List<Menu> findByRoleId(long roleId);
}
