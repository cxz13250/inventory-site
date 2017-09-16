package cn.iselab.inventory.site.dao;

import cn.iselab.inventory.site.model.Category;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by ROGK on 2017/9/15.
 */
@Transactional
public interface CategoryDao extends CrudRepository<Category, Long>{
}
