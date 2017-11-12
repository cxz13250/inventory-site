package cn.iselab.inventory.site.service;

import cn.iselab.inventory.site.model.SaleOrder;
import cn.iselab.inventory.site.model.SaleStrategy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:49 2017/11/6
 * @Modified By:
 */
public interface SaleStrategyService {

    SaleStrategy createSaleStrategy(SaleStrategy strategy);

    Page<SaleStrategy> getSaleStrategies(String keyword, Pageable pageable);

    SaleStrategy getSaleStrategy(long saleStrategyId);

    void updateSaleStrategy(SaleStrategy strategy);

    void deleteSaleStrategy(SaleStrategy strategy);
}
