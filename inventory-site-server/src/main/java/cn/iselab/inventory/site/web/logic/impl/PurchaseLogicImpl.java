package cn.iselab.inventory.site.web.logic.impl;

import cn.iselab.inventory.site.model.PurchaseOrder;
import cn.iselab.inventory.site.service.PurchaseOrderService;
import cn.iselab.inventory.site.web.data.PurchaseOrderVO;
import cn.iselab.inventory.site.web.data.wrapper.PurchaseOrderVOWrapper;
import cn.iselab.inventory.site.web.exception.HttpBadRequestException;
import cn.iselab.inventory.site.web.logic.PurchaseLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午5:58 2017/12/5
 * @Modified By:
 */
@Service
public class PurchaseLogicImpl implements PurchaseLogic {

    @Autowired
    PurchaseOrderService purchaseOrderService;

    @Autowired
    PurchaseOrderVOWrapper purchaseOrderVOWrapper;

    @Override
    public Page<PurchaseOrderVO> getPurchases(String keyword, Pageable pageable, Boolean type){
        Page<PurchaseOrder> orderPage=purchaseOrderService.getPurchaseOrders(keyword,pageable,type);
        return orderPage.map(new Converter<PurchaseOrder, PurchaseOrderVO>() {
            @Override
            public PurchaseOrderVO convert(PurchaseOrder order) {
                return purchaseOrderVOWrapper.wrap(order);
            }
        });
    }

    @Override
    public PurchaseOrderVO getPurchase(String orderId){
        PurchaseOrder order=purchaseOrderService.getPurchaseOrderByNum(orderId);
        if(order==null){
            throw new HttpBadRequestException("order not exists");
        }
        return purchaseOrderVOWrapper.wrap(order);
    }

    @Override
    public String createPurchase(PurchaseOrderVO vo){
        PurchaseOrder order=purchaseOrderVOWrapper.unwrap(vo);
        order=purchaseOrderService.createPurchaseOrder(order);
        return order.getNumber();
    }

    @Override
    public void updatePurchase(PurchaseOrderVO vo){
        PurchaseOrder order=purchaseOrderService.getPurchaseOrderByNum(vo.getNumber());
        if(order==null){
            throw new HttpBadRequestException("order not exists");
        }
        updateInfo(order,vo);
        purchaseOrderService.updatePurchaseOrder(order);
    }

    @Override
    public void deletePurchase(String orderId){
        PurchaseOrder order=purchaseOrderService.getPurchaseOrderByNum(orderId);
        if(order==null){
            throw new HttpBadRequestException("order not exists");
        }
        purchaseOrderService.deletePurchaseOrder(order);
    }

    private void updateInfo(PurchaseOrder order,PurchaseOrderVO vo){
        order.setTotal(vo.getTotal());
        order.setExtra(vo.getExtra());
        order.setRepository(vo.getRepository());
        order.setSupplier(vo.getSupplier());
        order.setOperator(vo.getOperator());
        if(vo.getStatus()!=null) {
            order.setStatus(vo.getStatus());
        }
    }
}
