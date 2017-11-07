package cn.iselab.inventory.site.dao;

import cn.iselab.inventory.site.model.StockOrder;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ROGK on 2017/11/1.
 */
public interface StockOrderDao extends CrudRepository<StockOrder,Long> ,JpaSpecificationExecutor<StockOrder>{
}
