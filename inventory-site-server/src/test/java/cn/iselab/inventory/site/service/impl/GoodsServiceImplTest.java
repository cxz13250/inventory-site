package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.Application;
import cn.iselab.inventory.site.dao.GoodsDao;
import cn.iselab.inventory.site.model.Goods;
import cn.iselab.inventory.site.service.GoodsService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
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
 * @Date: Created in 上午12:06 2017/11/7
 * @Modified By:
 */
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
public class GoodsServiceImplTest {

    @InjectMocks
    private GoodsService goodsService=new GoodsServiceImpl();

    @Mock
    private GoodsDao goodsDao;

    Goods good=new Goods();
    List<Goods> goods=new ArrayList<>();
    Page<Goods> goodsPage;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        good.setId(1L);
        good.setName("test");
        good.setCostPrice(1.0);
        good.setRetailPrice(1.0);
        good.setModel("test");
        good.setCategory(1L);

        goods.add(good);

        goodsPage=new PageImpl<Goods>(goods);
    }

    @Test
    public void should_createGood_when_givenGood() throws Exception {
        goodsService.createGood(good);

        Mockito.verify(goodsDao).save(any(Goods.class));
    }

    @Test
    public void should_returnGood_when_givenGoodId() throws Exception {
        when(goodsDao.findOne(anyLong())).thenReturn(good);

        Goods result=goodsService.getGoodById(1L);

        Assert.assertEquals(good.getId(),result.getId());
        Assert.assertEquals(good.getName(),result.getName());
        Assert.assertEquals(good.getCategory(),result.getCategory());
    }

    @Test
    public void getGoods() throws Exception {
        when(goodsDao.findAll(any(Specifications.class),any(Pageable.class))).thenReturn(goodsPage);

        Page<Goods> result=goodsService.getGoods(anyString(),any(Pageable.class));

        Assert.assertEquals(good,result.getContent().get(0));
    }

    @Test
    public void should_updateGood_when_givenGood() throws Exception {
        goodsService.updateGood(good);

        Mockito.verify(goodsDao).save(any(Goods.class));
    }

    @Test
    public void deleteGood() throws Exception {
        goodsService.deleteGood(good);

        Mockito.verify(goodsDao).delete(any(Goods.class));
    }

}