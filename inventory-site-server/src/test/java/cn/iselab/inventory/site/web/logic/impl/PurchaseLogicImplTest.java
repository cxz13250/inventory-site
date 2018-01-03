package cn.iselab.inventory.site.web.logic.impl;

import cn.iselab.inventory.site.Application;
import cn.iselab.inventory.site.model.GoodItem;
import cn.iselab.inventory.site.model.PurchaseOrder;
import cn.iselab.inventory.site.service.GoodItemService;
import cn.iselab.inventory.site.service.PurchaseOrderService;
import cn.iselab.inventory.site.web.data.PurchaseOrderVO;
import cn.iselab.inventory.site.web.data.wrapper.PurchaseOrderVOWrapper;
import cn.iselab.inventory.site.web.logic.PurchaseLogic;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午7:40 2017/12/5
 * @Modified By:
 */

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
public class PurchaseLogicImplTest {

    @InjectMocks
    PurchaseLogic purchaseLogic=new PurchaseLogicImpl();

    @Mock
    PurchaseOrderService purchaseOrderService;

    @Mock
    PurchaseOrderVOWrapper purchaseOrderVOWrapper;

    @Mock
    GoodItemService goodItemService;

    PurchaseOrder order=new PurchaseOrder();
    PurchaseOrderVO vo=new PurchaseOrderVO();
    GoodItem item=new GoodItem();
    List<PurchaseOrder> orders=new ArrayList<>();
    List<PurchaseOrderVO> orderVOS=new ArrayList<>();
    List<GoodItem> items=new ArrayList<>();
    Pageable pageable;
    Page<PurchaseOrder> orderPage;
    Page<PurchaseOrderVO> orderVOPage;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        order.setId(1L);
        order.setOperator("test");
        order.setTotal(1.0);
        order.setNumber("test");

        orders.add(order);

        vo.setOperator("test");
        vo.setTotal(1.0);
        vo.setNumber("test");
        vo.setStatus(1L);

        orderVOS.add(vo);

        item.setOrderId(1L);
        item.setNumber(1L);
        item.setGoodName("test");

        items.add(item);

        vo.setGoodsItemVOS(items);

        orderPage=new PageImpl<>(orders);
        orderVOPage=new PageImpl<>(orderVOS);

        pageable = new PageRequest(0,10);
    }

    @Test
    public void should_returnPurchaseOrders_when_PurchaseOrdersExist() throws Exception {
        when(purchaseOrderService.getPurchaseOrders(anyString(),any(Pageable.class),anyBoolean())).thenReturn(orderPage);
        when(purchaseOrderVOWrapper.wrap(any(PurchaseOrder.class))).thenReturn(vo);

        Page<PurchaseOrderVO> result=purchaseLogic.getPurchases("test",pageable,true);
        Assert.assertEquals(vo.getOperator(),result.getContent().get(0).getOperator());
        Assert.assertEquals(vo.getNumber(),result.getContent().get(0).getNumber());
        Assert.assertEquals(vo.getTotal(),result.getContent().get(0).getTotal());
    }

    @Test
    public void should_returnPurchaseOrder_when_givenPurchaseNumber() throws Exception {
        when(purchaseOrderService.getPurchaseOrderByNum(anyString())).thenReturn(order);
        when(purchaseOrderVOWrapper.wrap(any(PurchaseOrder.class))).thenReturn(vo);

        PurchaseOrderVO result=purchaseLogic.getPurchase("test");

        Assert.assertEquals(vo.getNumber(),result.getNumber());
        Assert.assertEquals(vo.getOperator(),result.getOperator());
        Assert.assertEquals(vo.getSupplier(),result.getSupplier());
    }

    @Test
    public void should_createPurchaseOrder_when_givenPurchaseOrder() throws Exception {
        when(purchaseOrderVOWrapper.unwrap(any(PurchaseOrderVO.class))).thenReturn(order);
        when(purchaseOrderService.createPurchaseOrder(any(PurchaseOrder.class))).thenReturn(order);

        String result=purchaseLogic.createPurchase(vo);

        Mockito.verify(goodItemService).createGoodItem(any(GoodItem.class));
        Assert.assertEquals(order.getNumber(),result);
    }

    @Test
    public void should_updatePurchaseOrder_when_givenPurchaseOrder() throws Exception {
        when(purchaseOrderService.getPurchaseOrderByNum(anyString())).thenReturn(order);

        purchaseLogic.updatePurchase(vo);

        Mockito.verify(purchaseOrderService).updatePurchaseOrder(any(PurchaseOrder.class));
    }

    @Test
    public void should_deletePurchaseOrder_when_givenPurchaseOrder() throws Exception {
        when(purchaseOrderService.getPurchaseOrderByNum(anyString())).thenReturn(order);

        purchaseLogic.deletePurchase("test");

        Mockito.verify(purchaseOrderService).deletePurchaseOrder(any(PurchaseOrder.class));
    }

}