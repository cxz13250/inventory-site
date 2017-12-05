package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.Application;
import cn.iselab.inventory.site.dao.SaleOrderDao;
import cn.iselab.inventory.site.model.SaleOrder;
import cn.iselab.inventory.site.service.SaleOrderService;
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

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:04 2017/11/7
 * @Modified By:
 */
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
public class SaleOrderServiceImplTest {

    @InjectMocks
    SaleOrderService saleOrderService=new SaleOrderServiceImpl();

    @Mock
    SaleOrderDao saleOrderDao;

    SaleOrder saleOrder=new SaleOrder();
    List<SaleOrder> saleOrders=new ArrayList<>();
    Pageable pageable;
    Page<SaleOrder> saleOrderPage;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        saleOrder.setId(1L);
        saleOrder.setCustomId(1L);
        saleOrder.setNumber("test");
        saleOrder.setOperator("test");

        saleOrders.add(saleOrder);

        saleOrderPage=new PageImpl<SaleOrder>(saleOrders);

        pageable=new PageRequest(0,10);
    }

    @Test
    public void should_createSaleOrder_when_givenOrder() throws Exception {
        when(saleOrderDao.save(any(SaleOrder.class))).thenReturn(saleOrder);

        SaleOrder result=saleOrderService.createSaleOrder(saleOrder);

        Assert.assertEquals(saleOrder.getId(),result.getId());
        Assert.assertEquals(saleOrder.getCustomId(),result.getCustomId());
        Assert.assertEquals(saleOrder.getNumber(),result.getNumber());
        Assert.assertEquals(saleOrder.getOperator(),result.getOperator());
    }

    @Test
    public void should_returnSaleOrder_when_givenKeyword() throws Exception {
        when(saleOrderDao.findAll(any(Specifications.class),any(Pageable.class))).thenReturn(saleOrderPage);

        Page<SaleOrder> result=saleOrderService.getSaleOrders("test",pageable,true);

        Assert.assertEquals(saleOrder,result.getContent().get(0));
    }

    @Test
    public void should_returnSaleOrder_when_givenSaleOrderId() throws Exception {
        when(saleOrderDao.findOne(anyLong())).thenReturn(saleOrder);

        SaleOrder result=saleOrderService.getSaleOrder(1L);

        Assert.assertEquals(saleOrder.getId(),result.getId());
        Assert.assertEquals(saleOrder.getCustomId(),result.getCustomId());
        Assert.assertEquals(saleOrder.getNumber(),result.getNumber());
        Assert.assertEquals(saleOrder.getOperator(),result.getOperator());
    }

    @Test
    public void should_updateSaleOrder_when_givenSaleOrder() throws Exception {
        saleOrderService.updateSaleOrder(saleOrder);

        Mockito.verify(saleOrderDao).save(any(SaleOrder.class));
    }

    @Test
    public void should_deleteSaleOrder_when_givenSaleOrder() throws Exception {
        saleOrderService.deleteSaleOrder(saleOrder);

        Mockito.verify(saleOrderDao).save(any(SaleOrder.class));
    }

}