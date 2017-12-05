package cn.iselab.inventory.site.web.logic;

import cn.iselab.inventory.site.web.data.PurchaseOrderVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午5:57 2017/12/5
 * @Modified By:
 */
public interface PurchaseLogic {

    Page<PurchaseOrderVO> getPurchases(String keyword, Pageable pageable,Boolean type);

    PurchaseOrderVO getPurchase(String orderId);

    String createPurchase(PurchaseOrderVO vo);

    void updatePurchase(PurchaseOrderVO vo);

    void deletePurchase(String orderId);
}
