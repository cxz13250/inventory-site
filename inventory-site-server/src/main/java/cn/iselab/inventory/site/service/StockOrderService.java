package cn.iselab.inventory.site.service;

import cn.iselab.inventory.site.model.SaleOrder;
import cn.iselab.inventory.site.model.StockOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:42 2017/11/6
 * @Modified By:
 */
public interface StockOrderService {

    StockOrder createStockOrder(StockOrder stockOrder);

    Page<StockOrder> getStockOrders(String keyword, Pageable pageable);

    StockOrder getStockOrder(long stockOrderId);

    void updateStockOrder(StockOrder stockOrder);

    void deleteStockOrder(StockOrder stockOrder);
}
