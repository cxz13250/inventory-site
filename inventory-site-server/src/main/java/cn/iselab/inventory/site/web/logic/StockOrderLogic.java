package cn.iselab.inventory.site.web.logic;

import cn.iselab.inventory.site.web.data.StockOrderVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午11:32 2017/12/7
 * @Modified By:
 */
public interface StockOrderLogic {

    Page<StockOrderVO> getStockOrders(String keyword, Pageable pageable, Long type);

    StockOrderVO getStockOrder(long id);

    Long createStockOrder(StockOrderVO vo);

    void updateStockOrder(StockOrderVO vo);

    void deleteStockOrder(long id);
}
