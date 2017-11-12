package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.Application;
import cn.iselab.inventory.site.dao.SaleStrategyDao;
import cn.iselab.inventory.site.model.SaleStrategy;
import cn.iselab.inventory.site.service.SaleStrategyService;
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
 * @Date: Created in 下午11:16 2017/11/12
 * @Modified By:
 */
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
public class SaleStrategyServiceImplTest {

    @InjectMocks
    private SaleStrategyService strategyService=new SaleStrategyServiceImpl();

    @Mock
    private SaleStrategyDao strategyDao;

    SaleStrategy strategy=new SaleStrategy();
    List<SaleStrategy> strategies=new ArrayList<>();
    Page<SaleStrategy> strategyPage;
    Pageable pageable;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        strategy.setContent("test");
        strategy.setId(1L);
        strategy.setName("test");

        strategies.add(strategy);

        strategyPage=new PageImpl<SaleStrategy>(strategies);

        pageable=new PageRequest(0,10);
    }

    @Test
    public void should_createSaleStrategy_when_givenSaleStrategy() throws Exception {
        when(strategyDao.save(any(SaleStrategy.class))).thenReturn(strategy);

        SaleStrategy result=strategyService.createSaleStrategy(strategy);

        Assert.assertEquals(result.getId(),strategy.getId());
        Assert.assertEquals(result.getContent(),strategy.getContent());
        Assert.assertEquals(result.getName(),strategy.getName());
    }

    @Test
    public void should_returnSaleStrategies_givenKeyword() throws Exception {
        when(strategyDao.findAll(any(Specifications.class),any(Pageable.class))).thenReturn(strategyPage);

        Page<SaleStrategy> result=strategyService.getSaleStrategies("test",pageable);

        Assert.assertEquals(strategy,result.getContent().get(0));
    }

    @Test
    public void should_returnSaleStrategy_when_givenId() throws Exception {
        when(strategyDao.findOne(anyLong())).thenReturn(strategy);

        SaleStrategy result=strategyService.getSaleStrategy(1L);

        Assert.assertEquals(result.getId(),strategy.getId());
        Assert.assertEquals(result.getContent(),strategy.getContent());
        Assert.assertEquals(result.getName(),strategy.getName());
    }

    @Test
    public void updateSaleStrategy() throws Exception {
        strategyService.updateSaleStrategy(strategy);

        Mockito.verify(strategyDao).save(any(SaleStrategy.class));
    }

    @Test
    public void deleteSaleStrategy() throws Exception {
        strategyService.deleteSaleStrategy(strategy);

        Mockito.verify(strategyDao).delete(any(SaleStrategy.class));
    }

}