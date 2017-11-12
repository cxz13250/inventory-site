package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.Application;
import cn.iselab.inventory.site.dao.GoodItemDao;
import cn.iselab.inventory.site.model.GoodItem;
import cn.iselab.inventory.site.service.GoodItemService;
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
import static org.mockito.Mockito.when;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 上午12:00 2017/11/13
 * @Modified By:
 */
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
public class GoodItemServiceImplTest {

    @InjectMocks
    private GoodItemService goodItemService=new GoodItemServiceImpl();

    @Mock
    private GoodItemDao goodItemDao;

    GoodItem goodItem=new GoodItem();
    List<GoodItem> goodItems=new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        goodItem.setId(1L);
        goodItem.setGoodId(1L);
        goodItem.setGoodName("test");
        goodItem.setNumber(1L);

        goodItems.add(goodItem);
    }

    @Test
    public void should_createGoodItem_when_givenGoodItem() throws Exception {
        when(goodItemDao.save(any(GoodItem.class))).thenReturn(goodItem);

        GoodItem result=goodItemService.createGoodItem(goodItem);

        Assert.assertEquals(goodItem.getId(),result.getId());
        Assert.assertEquals(goodItem.getGoodName(),result.getGoodName());
        Assert.assertEquals(goodItem.getNumber(),result.getNumber());
        Assert.assertEquals(goodItem.getGoodId(),result.getGoodId());
    }

    @Test
    public void should_returnGoodItems_when_givenOrderId() throws Exception {
        when(goodItemDao.findByOrderId(anyLong())).thenReturn(goodItems);

        List<GoodItem> result=goodItemService.getGoodItems(1L);

        Assert.assertEquals(result.get(0),goodItems.get(0));
    }

    @Test
    public void should_returnGoodItem_when_givenGoodItem() throws Exception {
        when(goodItemDao.findOne(anyLong())).thenReturn(goodItem);

        GoodItem result=goodItemService.getGoodItem(1L);

        Assert.assertEquals(goodItem.getId(),result.getId());
        Assert.assertEquals(goodItem.getGoodName(),result.getGoodName());
        Assert.assertEquals(goodItem.getNumber(),result.getNumber());
        Assert.assertEquals(goodItem.getGoodId(),result.getGoodId());
    }

    @Test
    public void should_updateGoodItem_when_givenGoodItem() throws Exception {
        goodItemService.updateGoodItem(goodItem);

        Mockito.verify(goodItemDao).save(any(GoodItem.class));
    }

    @Test
    public void should_deleteGoodItem_when_givenGoodItem() throws Exception {
        goodItemService.deleteGoodItem(goodItem);

        Mockito.verify(goodItemDao).delete(any(GoodItem.class));
    }
}