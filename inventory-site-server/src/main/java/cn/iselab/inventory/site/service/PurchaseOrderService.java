package cn.iselab.inventory.site.service;

import cn.iselab.inventory.site.model.PurchaseOrder;
import cn.iselab.inventory.site.model.Receipt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:47 2017/11/6
 * @Modified By:
 */
public interface PurchaseOrderService {

    PurchaseOrder createPurchaseOrder(PurchaseOrder order);

    Page<PurchaseOrder> getPurchaseOrders(String keyword, Pageable pageable, Boolean type);

    PurchaseOrder getPurchaseOrder(long orderId);

    PurchaseOrder getPurchaseOrderByNum(String number);

    void updatePurchaseOrder(PurchaseOrder order);

    void deletePurchaseOrder(PurchaseOrder order);
}
