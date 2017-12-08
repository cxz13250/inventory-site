package cn.iselab.inventory.site.service;

import cn.iselab.inventory.site.model.SaleOrderItem;

import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午3:20 2017/12/8
 * @Modified By:
 */
public interface SaleOrderItemService {

    SaleOrderItem createSaleItem(SaleOrderItem item);

    List<SaleOrderItem> getSaleItems(long orderId);
}
