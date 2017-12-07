package cn.iselab.inventory.site.web.logic;

import cn.iselab.inventory.site.web.data.SaleStrategyVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午11:09 2017/12/7
 * @Modified By:
 */
public interface SaleStrategyLogic {

    Page<SaleStrategyVO> getSaleStrategies(String keyword, Pageable pageable);

    SaleStrategyVO getSaleStrategy(long id);

    Long createSaleStrategy(SaleStrategyVO vo);

    void updateSaleStrategy(SaleStrategyVO vo);

    void deleteSaleStrategy(long id);
}
