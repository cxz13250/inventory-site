package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.Application;
import cn.iselab.inventory.site.dao.StockOrderDao;
import cn.iselab.inventory.site.model.SaleOrder;
import cn.iselab.inventory.site.model.StockOrder;
import cn.iselab.inventory.site.service.StockOrderService;
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
 * @Date: Created in 下午2:20 2017/11/7
 * @Modified By:
 */
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
public class StockOrderServiceImplTest {

    @InjectMocks
    StockOrderService stockOrderService=new StockOrderServiceImpl();

    @Mock
    StockOrderDao stockOrderDao;

    StockOrder stockOrder=new StockOrder();
    List<StockOrder> stockOrders=new ArrayList<>();
    Pageable pageable;
    Page<StockOrder> stockOrderPage;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        stockOrder.setId(1L);
        stockOrder.setGoodId(1L);
        stockOrder.setNumber(1L);

        stockOrders.add(stockOrder);

        stockOrderPage=new PageImpl<StockOrder>(stockOrders);

        pageable=new PageRequest(0,10);
    }

    @Test
    public void should_createStockOrder_when_givenStockOrder() throws Exception {
        when(stockOrderDao.save(any(StockOrder.class))).thenReturn(stockOrder);

        StockOrder result=stockOrderService.createStockOrder(stockOrder);

        Assert.assertEquals(stockOrder.getId(),result.getId());
        Assert.assertEquals(stockOrder.getGoodId(),result.getGoodId());
        Assert.assertEquals(stockOrder.getNumber(),result.getNumber());
    }

    @Test
    public void should_returnStockOrders_when_givenKeyword() throws Exception {
        when(stockOrderDao.findAll(any(Specifications.class),any(Pageable.class))).thenReturn(stockOrderPage);

        Page<StockOrder> result=stockOrderService.getStockOrders("test",pageable,1L);

        Assert.assertEquals(stockOrder,result.getContent().get(0));
    }

    @Test
    public void should_returnStockOrder_when_givenStockOrderId() throws Exception {
        when(stockOrderDao.findOne(anyLong())).thenReturn(stockOrder);

        StockOrder result=stockOrderService.getStockOrder(1L);

        Assert.assertEquals(stockOrder.getId(),result.getId());
        Assert.assertEquals(stockOrder.getGoodId(),result.getGoodId());
        Assert.assertEquals(stockOrder.getNumber(),result.getNumber());
    }

    @Test
    public void should_updateStockOrder_when_givenStockOrder() throws Exception {
        stockOrderService.updateStockOrder(stockOrder);

        Mockito.verify(stockOrderDao).save(any(StockOrder.class));
    }

    @Test
    public void should_deleteStockOrder_when_givenStockOrder() throws Exception {
        stockOrderService.deleteStockOrder(stockOrder);

        Mockito.verify(stockOrderDao).delete(any(StockOrder.class));
    }

}