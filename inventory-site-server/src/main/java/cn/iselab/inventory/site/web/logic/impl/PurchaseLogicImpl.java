package cn.iselab.inventory.site.web.logic.impl;

import cn.iselab.inventory.site.common.constanst.OrderStatusConstants;
import cn.iselab.inventory.site.model.Custom;
import cn.iselab.inventory.site.model.GoodItem;
import cn.iselab.inventory.site.model.Goods;
import cn.iselab.inventory.site.model.PurchaseOrder;
import cn.iselab.inventory.site.service.CustomService;
import cn.iselab.inventory.site.service.GoodItemService;
import cn.iselab.inventory.site.service.GoodsService;
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

import java.util.List;

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

    @Autowired
    GoodItemService goodItemService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    CustomService customService;

    @Autowired
    GoodItemService itemService;

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
        PurchaseOrderVO vo=purchaseOrderVOWrapper.wrap(order);
        vo.setGoodsItemVOS(goodItemService.getGoodItems(order.getId()));
        return vo;
    }

    @Override
    public String createPurchase(PurchaseOrderVO vo){
        PurchaseOrder order=purchaseOrderVOWrapper.unwrap(vo);
        order=purchaseOrderService.createPurchaseOrder(order);
        List<GoodItem> items=vo.getGoodsItemVOS();
        for (GoodItem item:items){
            item.setOrderId(order.getId());
            goodItemService.createGoodItem(item);
        }
        return order.getNumber();
    }

    @Override
    public void updatePurchase(PurchaseOrderVO vo){
        PurchaseOrder order=purchaseOrderService.getPurchaseOrderByNum(vo.getNumber());
        if(order==null){
            throw new HttpBadRequestException("order not exists");
        }
        updateInfo(order,vo);
        order=purchaseOrderService.updatePurchaseOrder(order);
        if(vo.getStatus()== OrderStatusConstants.APPROVED){
            checkPurchase(order);
        }
    }

    @Override
    public void deletePurchase(String orderId){
        PurchaseOrder order=purchaseOrderService.getPurchaseOrderByNum(orderId);
        if(order==null){
            throw new HttpBadRequestException("order not exists");
        }
        purchaseOrderService.deletePurchaseOrder(order);
    }

    @Override
    public void checkPurchase(PurchaseOrder order){
        Custom custom=customService.getCustom(order.getSupplier());
        custom.setPay(custom.getReceive()+order.getTotal());
        customService.updateCustom2(custom);
        List<GoodItem> items=itemService.getGoodItems(order.getId());
        for (GoodItem item:items){
            Goods goods=goodsService.getGoodById(item.getGoodId());
            goods.setInventory(goods.getInventory()+item.getNumber());
            goodsService.updateGood(goods);
        }
    }

    private void updateInfo(PurchaseOrder order,PurchaseOrderVO vo){
        order.setTotal(vo.getTotal());
        order.setExtra(vo.getExtra());
        order.setRepository(vo.getRepository());
        order.setSupplier(vo.getCustomId());
        order.setOperator(vo.getOperator());
        order.setStatus(vo.getStatus());
        if(vo.getStatus()!=null) {
            order.setStatus(vo.getStatus());
        }
    }
}
