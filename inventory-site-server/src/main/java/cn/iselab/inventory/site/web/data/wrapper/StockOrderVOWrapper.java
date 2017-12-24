package cn.iselab.inventory.site.web.data.wrapper;

import cn.iselab.inventory.site.model.StockOrder;
import cn.iselab.inventory.site.service.GoodsService;
import cn.iselab.inventory.site.web.data.StockOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午11:46 2017/12/7
 * @Modified By:
 */
@Service
public class StockOrderVOWrapper extends BaseWrapper<StockOrderVO,StockOrder>{

    @Autowired
    GoodsService goodsService;

    @Override
    public StockOrderVO wrap(StockOrder order){
        StockOrderVO vo=new StockOrderVO();
        vo.setCreateTime(order.getCreateTime().getTime());
        vo.setId(order.getId());
        vo.setNumber(order.getNumber());
        vo.setGoodId(order.getGoodId());
        vo.setGoodName(goodsService.getGoodById(order.getGoodId()).getName());
        vo.setStatus(order.getStatus());
        vo.setType(order.getType());
        return vo;
    }

    @Override
    public StockOrder unwrap(StockOrderVO vo){
        StockOrder order=new StockOrder();
        order.setType(vo.getType());
        order.setGoodId(vo.getGoodId());
        order.setNumber(vo.getNumber());
        order.setStatus(vo.getStatus());
        return order;
    }
}
