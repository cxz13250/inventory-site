package cn.iselab.inventory.site.service;

import cn.iselab.inventory.site.model.Receipt;
import cn.iselab.inventory.site.model.SaleOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:42 2017/11/6
 * @Modified By:
 */
public interface SaleOrderService {

    SaleOrder createSaleOrder(SaleOrder saleOrder);

    Page<SaleOrder> getSaleOrders(String keyword, Pageable pageable);

    SaleOrder getSaleOrder(long saleOrderId);

    void updateSaleOrder(SaleOrder saleOrder);

    void deleteSaleOrder(SaleOrder saleOrder);
}
