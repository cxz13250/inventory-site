package cn.iselab.inventory.site.web.logic.impl;

import cn.iselab.inventory.site.Application;
import cn.iselab.inventory.site.model.Goods;
import cn.iselab.inventory.site.service.GoodsService;
import cn.iselab.inventory.site.web.data.GoodsVO;
import cn.iselab.inventory.site.web.data.wrapper.GoodsVOWrapper;
import cn.iselab.inventory.site.web.logic.GoodsLogic;
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
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午10:16 2017/12/4
 * @Modified By:
 */

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
public class GoodsLogicImplTest {

    @InjectMocks
    GoodsLogic goodsLogic=new GoodsLogicImpl();

    @Mock
    GoodsService goodsService;

    @Mock
    GoodsVOWrapper goodsVOWrapper;

    Page<GoodsVO> goodsVOPage;
    Page<Goods> goodsPage;
    List<GoodsVO> goodsVOS=new ArrayList<>();
    List<Goods> goods=new ArrayList<>();
    GoodsVO goodsVO=new GoodsVO();
    Goods good=new Goods();
    Pageable pageable;

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

        goodsVO.setId(1L);
        goodsVO.setName("test");
        goodsVO.setCostPrice(1.0);
        goodsVO.setRetailPrice(1.0);
        goodsVO.setModel("test");
        goodsVO.setCategory(1L);

        goodsVOS.add(goodsVO);

        goodsVOPage=new PageImpl<GoodsVO>(goodsVOS);
        goodsPage=new PageImpl<Goods>(goods);

        pageable=new PageRequest(0,10);
    }

    @Test
    public void should_returnGoodsList_when_ListExists() throws Exception {
        when(goodsService.getGoods(anyString(),any(Pageable.class))).thenReturn(goodsPage);
        when(goodsVOWrapper.wrap(any(Goods.class))).thenReturn(goodsVO);

        Page<GoodsVO> result=goodsLogic.getGoodsList("test",pageable);
        Assert.assertEquals(goodsVO.getName(),result.getContent().get(0).getName());
        Assert.assertEquals(goodsVO.getCategory(),result.getContent().get(0).getCategory());
        Assert.assertEquals(goodsVO.getCostPrice(),result.getContent().get(0).getCostPrice());
    }

    @Test
    public void should_returnGoods_when_givenGoodsId() throws Exception {
        when(goodsService.getGoodById(anyLong())).thenReturn(good);
        when(goodsVOWrapper.wrap(any(Goods.class))).thenReturn(goodsVO);

        GoodsVO result=goodsLogic.getGoods(1L);
        Assert.assertEquals(goodsVO.getCategory(),result.getCategory());
        Assert.assertEquals(goodsVO.getCostPrice(),result.getCostPrice());
        Assert.assertEquals(goodsVO.getName(),result.getName());
    }

    @Test
    public void should_createGoods_when_givenGoodsVO() throws Exception {
        when(goodsVOWrapper.unwrap(any(GoodsVO.class))).thenReturn(good);
        when(goodsService.createGood(any(Goods.class))).thenReturn(good);

        Long result=goodsLogic.createGoods(goodsVO);

        Assert.assertEquals((Long)good.getId(),result);
    }

    @Test
    public void should_updateGoods_when_givenGoodsVO() throws Exception {
        when(goodsService.getGoodById(anyLong())).thenReturn(good);

        goodsLogic.updateGoods(goodsVO);

        Mockito.verify(goodsService).updateInfo(any(Goods.class),any(GoodsVO.class));
    }

    @Test
    public void should_deleteGoods_when_givenGoodsId() throws Exception {
        when(goodsService.getGoodById(anyLong())).thenReturn(good);

        goodsLogic.deleteGoods(1L);

        Mockito.verify(goodsService).deleteGoods(any(Goods.class));
    }

}