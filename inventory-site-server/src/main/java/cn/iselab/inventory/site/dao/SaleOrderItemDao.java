package cn.iselab.inventory.site.dao;

import cn.iselab.inventory.site.model.SaleOrderItem;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午3:20 2017/12/8
 * @Modified By:
 */
@Transactional
public interface SaleOrderItemDao extends CrudRepository<SaleOrderItem,Long>{

    List<SaleOrderItem> findByOrderId(long orderId);
}
