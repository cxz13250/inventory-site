package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.Application;
import cn.iselab.inventory.site.dao.PurchaseOrderDao;
import cn.iselab.inventory.site.model.PurchaseOrder;
import cn.iselab.inventory.site.service.PurchaseOrderService;
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
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:39 2017/11/7
 * @Modified By:
 */
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
public class PurchaseOrderImplTest {

    @InjectMocks
    PurchaseOrderService purchaseOrderService=new PurchaseOrderImpl();

    @Mock
    PurchaseOrderDao purchaseOrderDao;

    PurchaseOrder order=new PurchaseOrder();
    List<PurchaseOrder> orders=new ArrayList<>();
    Pageable pageable;
    Page<PurchaseOrder> orderPage;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        order.setId(1L);
        order.setOperator("test");
        order.setTotal(1.0);
        order.setNumber("test");

        orders.add(order);

        orderPage=new PageImpl<PurchaseOrder>(orders);

        pageable = new PageRequest(0,10);
    }

    @Test
    public void should_createPurchaseOrder_when_givenPurchaseOrder() throws Exception {
        when(purchaseOrderDao.save(any(PurchaseOrder.class))).thenReturn(order);

        PurchaseOrder result=purchaseOrderService.createPurchaseOrder(order);

        Assert.assertEquals(order.getId(),result.getId());
        Assert.assertEquals(order.getNumber(),result.getNumber());
        Assert.assertEquals(order.getOperator(),result.getOperator());
    }

    @Test
    public void should_returnPurchaseOrders_when_givenKeyword() throws Exception {
        when(purchaseOrderDao.findAll(any(Specifications.class),any(Pageable.class))).thenReturn(orderPage);

        Page<PurchaseOrder> result=purchaseOrderService.getPurchaseOrders("test",pageable);

        Assert.assertEquals(order,result.getContent().get(0));
    }

    @Test
    public void should_returnPurchaseOrder_when_givenPurchaseId() throws Exception {
        when(purchaseOrderDao.findOne(anyLong())).thenReturn(order);

        PurchaseOrder result=purchaseOrderService.getPurchaseOrder(1L);

        Assert.assertEquals(order.getId(),result.getId());
        Assert.assertEquals(order.getNumber(),result.getNumber());
        Assert.assertEquals(order.getOperator(),result.getOperator());
    }

    @Test
    public void should_updatePurchaseOrder_when_givenPurchaseOrder() throws Exception {
        purchaseOrderService.updatePurchaseOrder(order);

        Mockito.verify(purchaseOrderDao).save(any(PurchaseOrder.class));
    }

    @Test
    public void should_deletePurchaseOrder_when_givenPurchaseOrder() throws Exception {
        purchaseOrderService.deletePurchaseOrder(order);

        Mockito.verify(purchaseOrderDao).delete(any(PurchaseOrder.class));
    }

}