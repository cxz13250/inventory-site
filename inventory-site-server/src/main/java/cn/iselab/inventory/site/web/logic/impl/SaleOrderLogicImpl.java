package cn.iselab.inventory.site.web.logic.impl;

import cn.iselab.inventory.site.common.constanst.OrderStatusConstants;
import cn.iselab.inventory.site.model.Custom;
import cn.iselab.inventory.site.model.Goods;
import cn.iselab.inventory.site.model.SaleOrder;
import cn.iselab.inventory.site.model.SaleOrderItem;
import cn.iselab.inventory.site.service.CustomService;
import cn.iselab.inventory.site.service.GoodsService;
import cn.iselab.inventory.site.service.SaleOrderItemService;
import cn.iselab.inventory.site.service.SaleOrderService;
import cn.iselab.inventory.site.web.data.SaleOrderItemVO;
import cn.iselab.inventory.site.web.data.SaleOrderVO;
import cn.iselab.inventory.site.web.data.wrapper.GoodsVOWrapper;
import cn.iselab.inventory.site.web.data.wrapper.SaleOrderItemVOWrapper;
import cn.iselab.inventory.site.web.data.wrapper.SaleOrderVOWraper;
import cn.iselab.inventory.site.web.exception.HttpBadRequestException;
import cn.iselab.inventory.site.web.logic.SaleOrderLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午8:07 2017/12/5
 * @Modified By:
 */

@Service
public class SaleOrderLogicImpl implements SaleOrderLogic{

    @Autowired
    SaleOrderService orderService;

    @Autowired
    SaleOrderVOWraper saleOrderVOWraper;

    @Autowired
    CustomService customService;

    @Autowired
    SaleOrderItemService saleOrderItemService;

    @Autowired
    SaleOrderItemVOWrapper itemVOWrapper;

    @Autowired
    GoodsService goodsService;

    @Override
    public Page<SaleOrderVO> getSaleOrders(String keyword, Pageable pageable, Boolean type){
        Page<SaleOrder> orders=orderService.getSaleOrders(keyword,pageable,type);
        return orders.map(new Converter<SaleOrder, SaleOrderVO>() {
            @Override
            public SaleOrderVO convert(SaleOrder saleOrder) {
                return saleOrderVOWraper.wrap(saleOrder);
            }
        });
    }

    @Override
    public SaleOrderVO getSaleOrder(String number){
        SaleOrder order=orderService.getSaleOrderByNum(number);
        if(order==null) {
            throw new HttpBadRequestException("order not exists");
        }
        SaleOrderVO vo= saleOrderVOWraper.wrap(order);
        List<SaleOrderItem> items=saleOrderItemService.getSaleItems(order.getId());
        List<SaleOrderItemVO> itemVOS=new ArrayList<>();
        for (SaleOrderItem item:items){
            SaleOrderItemVO vo1=itemVOWrapper.wrap(item);
            itemVOS.add(vo1);
        }
        vo.setOrderItems(itemVOS);
        return vo;
    }

    @Override
    public String createSaleOrder(SaleOrderVO vo){
        SaleOrder order=saleOrderVOWraper.unwrap(vo);
        Custom custom=customService.getCustom(order.getCustomId());
        order.setSaleman(custom.getSalesman());
        order=orderService.createSaleOrder(order);
        List<SaleOrderItemVO> items=vo.getOrderItems();
        for(SaleOrderItemVO vo1:items){
            SaleOrderItem item=itemVOWrapper.unwrap(vo1);
            item.setOrderId(order.getId());
            saleOrderItemService.createSaleItem(item);
        }
        return order.getNumber();
    }

    @Override
    public void updateSaleOrder(SaleOrderVO vo){
        SaleOrder order=orderService.getSaleOrderByNum(vo.getNumber());
        if(order==null) {
            throw new HttpBadRequestException("order not exists");
        }
        updateInfo(order,vo);
        if(vo.getStatus()== OrderStatusConstants.APPROVED) {
            orderService.updateSaleOrder(order);
        }
    }

    @Override
    public void checkoutSaleOrder(SaleOrder order){
        Custom custom=customService.getCustom(order.getCustomId());
        custom.setPay(custom.getPay()+order.getFinalTotal());
        customService.updateCustom2(custom);
        List<SaleOrderItem> items=saleOrderItemService.getSaleItems(order.getId());
        for (SaleOrderItem item:items){
            Goods goods=goodsService.getGoodById(item.getGoodId());
            goods.setInventory(goods.getInventory()-item.getSum());
            goodsService.updateGood(goods);
        }
    }

    @Override
    public void deleteSaleOrder(String number){
        SaleOrder order=orderService.getSaleOrderByNum(number);
        if(order==null) {
            throw new HttpBadRequestException("order not exists");
        }
        orderService.deleteSaleOrder(order);
    }

    private void updateInfo(SaleOrder order,SaleOrderVO vo){
        order.setTotal(vo.getTotal());
        order.setExtra(vo.getExtra());
        order.setRepository(vo.getRepository());
        order.setCustomId(vo.getCustomId());
        order.setOperator(vo.getOperator());
        if(vo.getDiscount()!=null) {
            order.setDiscount(vo.getDiscount());
        }
        if(vo.getStatus()!=null) {
            order.setStatus(vo.getStatus());
        }
    }
}
