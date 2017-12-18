package cn.iselab.inventory.site.dao;

import cn.iselab.inventory.site.model.Category;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by ROGK on 2017/9/15.
 */
@Transactional
public interface CategoryDao extends CrudRepository<Category, Long>,JpaSpecificationExecutor<Category>,PagingAndSortingRepository<Category,Long>{

    @Query("select c from Category c where c.deleted = 0")
    List<Category> findAllCategories();

    @Query("select c from Category c where c.name = :name")
    List<Category> findByName(@Param("name") String name);

    @Query(value = "select * from category where category.is_delete = 0 AND category.id not in (SELECT DISTINCT category from goods)",nativeQuery = true)
    List<Category> findForGoods();
}
