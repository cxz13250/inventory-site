package cn.iselab.inventory.site.web.logic.impl;

import cn.iselab.inventory.site.model.Custom;
import cn.iselab.inventory.site.model.SaleOrder;
import cn.iselab.inventory.site.service.CustomService;
import cn.iselab.inventory.site.service.SaleOrderService;
import cn.iselab.inventory.site.web.data.SaleOrderVO;
import cn.iselab.inventory.site.web.data.wrapper.SaleOrderVOWraper;
import cn.iselab.inventory.site.web.exception.HttpBadRequestException;
import cn.iselab.inventory.site.web.logic.SaleOrderLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        return saleOrderVOWraper.wrap(order);
    }

    @Override
    public String createSaleOrder(SaleOrderVO vo){
        SaleOrder order=saleOrderVOWraper.unwrap(vo);
        Custom custom=customService.getCustom(order.getCustomId());
        order.setSaleman(custom.getSalesman());
        order=orderService.createSaleOrder(order);
        return order.getNumber();
    }

    @Override
    public void updateSaleOrder(SaleOrderVO vo){
        SaleOrder order=orderService.getSaleOrderByNum(vo.getNumber());
        if(order==null) {
            throw new HttpBadRequestException("order not exists");
        }
        updateInfo(order,vo);
        orderService.updateSaleOrder(order);
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
