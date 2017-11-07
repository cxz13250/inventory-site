package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.Application;
import cn.iselab.inventory.site.dao.SaleDetailDao;
import cn.iselab.inventory.site.dao.SaleOrderDao;
import cn.iselab.inventory.site.model.Receipt;
import cn.iselab.inventory.site.model.SaleDetail;
import cn.iselab.inventory.site.model.SaleOrder;
import cn.iselab.inventory.site.service.SaleDetailService;
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
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午3:01 2017/11/7
 * @Modified By:
 */
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
public class SaleDetailServiceImplTest {

    @InjectMocks
    SaleDetailService saleDetailService=new SaleDetailServiceImpl();

    @Mock
    SaleDetailDao saleDetailDao;

    SaleDetail saleDetail=new SaleDetail();
    List<SaleDetail> saleDetails=new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        saleDetail.setId(1L);
        saleDetail.setGoodName("test");
        saleDetail.setModel("test");
        saleDetail.setNumber(1L);

        saleDetails.add(saleDetail);
    }

    @Test
    public void should_createSaleDetail_when_givenSaleDetail() throws Exception {
        when(saleDetailDao.save(any(SaleDetail.class))).thenReturn(saleDetail);

        SaleDetail result=saleDetailService.createSaleDetail(saleDetail);

        Assert.assertEquals(saleDetail.getId(),result.getId());
        Assert.assertEquals(saleDetail.getGoodName(),result.getGoodName());
        Assert.assertEquals(saleDetail.getModel(),result.getModel());
        Assert.assertEquals(saleDetail.getNumber(),result.getNumber());
    }

    @Test
    public void should_returnSaleDetails_when_givenGoodName() throws Exception {
        when(saleDetailDao.findByGoodName(anyString())).thenReturn(saleDetails);

        List<SaleDetail> result=saleDetailService.getSaleDetails("test");

        Assert.assertEquals(saleDetail.getId(),result.get(0).getId());
        Assert.assertEquals(saleDetail.getGoodName(),result.get(0).getGoodName());
        Assert.assertEquals(saleDetail.getModel(),result.get(0).getModel());
        Assert.assertEquals(saleDetail.getNumber(),result.get(0).getNumber());
    }

    @Test
    public void should_returnSaleDetail_when_givenSaleDetailId() throws Exception {
        when(saleDetailDao.findOne(anyLong())).thenReturn(saleDetail);

        SaleDetail result=saleDetailService.getSaleDetail(1L);

        Assert.assertEquals(saleDetail.getId(),result.getId());
        Assert.assertEquals(saleDetail.getModel(),result.getModel());
        Assert.assertEquals(saleDetail.getGoodName(),result.getGoodName());
        Assert.assertEquals(saleDetail.getNumber(),result.getNumber());
    }

    @Test
    public void should_updateSaleDetail_when_givenSaleDetail() throws Exception {
        saleDetailService.updateSaleDetail(saleDetail);

        Mockito.verify(saleDetailDao).save(any(SaleDetail.class));
    }

    @Test
    public void should_deleteSaleDetail_when_givenSaleDetail() throws Exception {
        saleDetailService.deleteSaleDetail(saleDetail);

        Mockito.verify(saleDetailDao).delete(any(SaleDetail.class));
    }

}