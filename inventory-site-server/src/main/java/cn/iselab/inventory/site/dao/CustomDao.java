package cn.iselab.inventory.site.dao;

import cn.iselab.inventory.site.model.Custom;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by ROGK on 2017/9/15.
 */
@Transactional
public interface CustomDao extends CrudRepository<Custom, Long>, JpaSpecificationExecutor<Custom>{

    @Query("select c from Custom c where c.isDelete = 0")
    List<Custom> getAll();

    @Query("select c from Custom c where c.isDelete = 0 and c.type = 2")
    List<Custom> getAllForSale();

    @Query("select c from Custom c where c.isDelete = 0 and c.type = 1")
    List<Custom> getAllForPurchase();
}
