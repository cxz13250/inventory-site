package cn.iselab.inventory.site.web.data.wrapper;

import cn.iselab.inventory.site.model.SaleStrategy;
import cn.iselab.inventory.site.web.data.SaleStrategyVO;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

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
        vo.setId(strategy.getId());
        vo.setContent(strategy.getContent());
        vo.setName(strategy.getName());
        vo.setStartTime(strategy.getStartTime().getTime());
        vo.setEndTime(strategy.getEndTime().getTime());
        return vo;
    }

    @Override
    public SaleStrategy unwrap(SaleStrategyVO vo){
        SaleStrategy strategy=new SaleStrategy();
        strategy.setContent(vo.getContent());
        strategy.setName(vo.getName());
        strategy.setStartTime(new Timestamp(vo.getStartTime()));
        strategy.setEndTime(new Timestamp(vo.getEndTime()));
        return strategy;
    }
}
