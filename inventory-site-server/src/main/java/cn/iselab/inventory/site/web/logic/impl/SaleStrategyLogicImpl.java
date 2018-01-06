package cn.iselab.inventory.site.web.logic.impl;

import cn.iselab.inventory.site.common.constanst.DeleteStatus;
import cn.iselab.inventory.site.model.SaleStrategy;
import cn.iselab.inventory.site.service.SaleStrategyService;
import cn.iselab.inventory.site.web.data.SaleStrategyVO;
import cn.iselab.inventory.site.web.data.wrapper.SaleStrategyVOWrapper;
import cn.iselab.inventory.site.web.exception.HttpBadRequestException;
import cn.iselab.inventory.site.web.logic.SaleStrategyLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午11:09 2017/12/7
 * @Modified By:
 */
@Service
public class SaleStrategyLogicImpl implements SaleStrategyLogic {

    @Autowired
    SaleStrategyService strategyService;

    @Autowired
    SaleStrategyVOWrapper saleStrategyVOWrapper;

    @Override
    public Page<SaleStrategyVO> getSaleStrategies(String keyword, Pageable pageable){
        Page<SaleStrategy> saleStrategies=strategyService.getSaleStrategies(keyword,pageable);
        return saleStrategies.map(new Converter<SaleStrategy, SaleStrategyVO>() {
            @Override
            public SaleStrategyVO convert(SaleStrategy saleStrategy) {
                return saleStrategyVOWrapper.wrap(saleStrategy);
            }
        });
    }

    @Override
    public SaleStrategyVO getSaleStrategy(long id){
        SaleStrategy strategy=strategyService.getSaleStrategy(id);
        if (strategy == null) {
            throw new HttpBadRequestException("saleStrategy not exists");
        }
        return saleStrategyVOWrapper.wrap(strategy);
    }

    @Override
    public Long createSaleStrategy(SaleStrategyVO vo){
        SaleStrategy strategy=saleStrategyVOWrapper.unwrap(vo);
        strategy=strategyService.createSaleStrategy(strategy);
        return strategy.getId();
    }

    @Override
    public void updateSaleStrategy(SaleStrategyVO vo){
        SaleStrategy strategy=strategyService.getSaleStrategy(vo.getId());
        if (strategy == null) {
            throw new HttpBadRequestException("saleStrategy not exists");
        }
        updateInfo(strategy,vo);
        strategyService.updateSaleStrategy(strategy);
    }

    @Override
    public void deleteSaleStrategy(long id){
        SaleStrategy strategy=strategyService.getSaleStrategy(id);
        if (strategy == null) {
            throw new HttpBadRequestException("saleStrategy not exists");
        }
        strategy.setDelete(DeleteStatus.IS_DELETE);
        strategyService.updateSaleStrategy(strategy);
    }

    private void updateInfo(SaleStrategy strategy,SaleStrategyVO vo){
        strategy.setName(vo.getName());
        strategy.setContent(vo.getContent());
        strategy.setStartTime(new Timestamp(vo.getStartTime()));
        strategy.setEndTime(new Timestamp(vo.getEndTime()));
    }
}
