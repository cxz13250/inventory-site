package cn.iselab.inventory.site.web.logic;

import cn.iselab.inventory.site.model.SaleOrder;
import cn.iselab.inventory.site.web.data.SaleOrderVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午8:07 2017/12/5
 * @Modified By:
 */
public interface SaleOrderLogic {

    Page<SaleOrderVO> getSaleOrders(String keyword, Pageable pageable, Boolean type);

    SaleOrderVO getSaleOrder(String number);

    String createSaleOrder(SaleOrderVO vo);

    void updateSaleOrder(SaleOrderVO vo);

    void checkoutSaleOrder(SaleOrder order);

    void deleteSaleOrder(String number);
}
