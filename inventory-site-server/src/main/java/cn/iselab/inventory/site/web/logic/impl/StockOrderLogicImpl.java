package cn.iselab.inventory.site.web.logic.impl;

import cn.iselab.inventory.site.common.constanst.DeleteStatus;
import cn.iselab.inventory.site.common.constanst.OrderStatusConstants;
import cn.iselab.inventory.site.common.constanst.StockOrderConstants;
import cn.iselab.inventory.site.model.Goods;
import cn.iselab.inventory.site.model.StockOrder;
import cn.iselab.inventory.site.service.GoodsService;
import cn.iselab.inventory.site.service.StockOrderService;
import cn.iselab.inventory.site.web.data.StockOrderVO;
import cn.iselab.inventory.site.web.data.wrapper.StockOrderVOWrapper;
import cn.iselab.inventory.site.web.exception.HttpBadRequestException;
import cn.iselab.inventory.site.web.logic.StockOrderLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午11:32 2017/12/7
 * @Modified By:
 */
@Service
public class StockOrderLogicImpl implements StockOrderLogic {

    @Autowired
    StockOrderService stockOrderService;

    @Autowired
    StockOrderVOWrapper stockOrderVOWrapper;

    @Autowired
    GoodsService goodsService;

    @Override
    public Page<StockOrderVO> getStockOrders(String keyword, Pageable pageable, Long type){
        Page<StockOrder> stockOrders=stockOrderService.getStockOrders(keyword,pageable,type);
        return stockOrders.map(new Converter<StockOrder, StockOrderVO>() {
            @Override
            public StockOrderVO convert(StockOrder stockOrder) {
                return stockOrderVOWrapper.wrap(stockOrder);
            }
        });
    }

    @Override
    public StockOrderVO getStockOrder(long id){
        StockOrder order=stockOrderService.getStockOrder(id);
        if (order == null) {
            throw new HttpBadRequestException("stockOrder not exists");
        }
        return stockOrderVOWrapper.wrap(order);
    }

    @Override
    public Long createStockOrder(StockOrderVO vo){
        StockOrder order=stockOrderVOWrapper.unwrap(vo);
        order=stockOrderService.createStockOrder(order);
        return order.getId();
    }

    @Override
    public void updateStockOrder(StockOrderVO vo){
        StockOrder order=stockOrderService.getStockOrder(vo.getId());
        if (order == null) {
            throw new HttpBadRequestException("stockOrder not exists");
        }
        updateInfo(order,vo);
        stockOrderService.updateStockOrder(order);
        if(vo.getStatus()== OrderStatusConstants.APPROVED){
            checkStockOrder(order);
        }
    }

    @Override
    public void deleteStockOrder(long id){
        StockOrder order=stockOrderService.getStockOrder(id);
        if (order == null) {
            throw new HttpBadRequestException("stockOrder not exists");
        }
        order.setDelete(DeleteStatus.IS_DELETE);
        stockOrderService.updateStockOrder(order);
    }

    @Override
    public void checkStockOrder(StockOrder order){
        Goods goods=goodsService.getGoodById(order.getGoodId());
        if(order.getType()== StockOrderConstants.LOSE){
            goods.setInventory(goods.getInventory()-order.getNumber());
        }else if(order.getType()==StockOrderConstants.SPILL){
            goods.setInventory(goods.getInventory()+order.getNumber());
        }
        goodsService.updateGood(goods);
    }

    private void updateInfo(StockOrder order,StockOrderVO vo){
        order.setNumber(vo.getNumber());
        order.setStatus(vo.getStatus());
        order.setGoodId(vo.getGoodId());
    }
}
