package cn.iselab.inventory.site.dao;

import cn.iselab.inventory.site.model.Category;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by ROGK on 2017/9/15.
 */
@Transactional
public interface CategoryDao extends CrudRepository<Category, Long>{

    @Query("select c from Category c")
    List<Category> findAllCategory();
}
