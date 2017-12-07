package cn.iselab.inventory.site.web.data.wrapper;

import cn.iselab.inventory.site.model.SaleStrategy;
import cn.iselab.inventory.site.web.data.SaleStrategyVO;
import org.springframework.stereotype.Service;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:38 2017/12/8
 * @Modified By:
 */
@Service
public class SaleStrategyVOWrapper extends BaseWrapper<SaleStrategyVO,SaleStrategy> {

    @Override
    public SaleStrategyVO wrap(SaleStrategy strategy){
        SaleStrategyVO vo=new SaleStrategyVO();
        return vo;
    }

    @Override
    public SaleStrategy unwrap(SaleStrategyVO vo){
        SaleStrategy strategy=new SaleStrategy();
        return strategy;
    }
}
