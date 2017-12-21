package cn.iselab.inventory.site.dao;

import cn.iselab.inventory.site.model.Goods;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by ROGK on 2017/9/15.
 */
@Transactional
public interface GoodsDao extends CrudRepository<Goods, Long>, JpaSpecificationExecutor<Goods>{

    @Query("select c from Goods c where c.deleted = 0")
    List<Goods> getAll();
}
