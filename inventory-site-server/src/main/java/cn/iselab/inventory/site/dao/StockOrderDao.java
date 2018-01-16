package cn.iselab.inventory.site.dao;

import cn.iselab.inventory.site.model.StockOrder;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by ROGK on 2017/11/1.
 */
public interface StockOrderDao extends CrudRepository<StockOrder,Long> ,JpaSpecificationExecutor<StockOrder>{

    @Query("select s from StockOrder s where s.goodId=:goodId and s.status=2")
    List<StockOrder> findByGoodId(@Param(value = "goodId") long goodId);
}
