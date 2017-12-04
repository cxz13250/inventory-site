package cn.iselab.inventory.site.web.logic.impl;

import cn.iselab.inventory.site.model.Goods;
import cn.iselab.inventory.site.service.GoodsService;
import cn.iselab.inventory.site.web.data.GoodsVO;
import cn.iselab.inventory.site.web.data.wrapper.GoodsVOWrapper;
import cn.iselab.inventory.site.web.exception.HttpBadRequestException;
import cn.iselab.inventory.site.web.logic.GoodsLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午8:48 2017/12/4
 * @Modified By:
 */
@Service
public class GoodsLogicImpl implements GoodsLogic{

    @Autowired
    GoodsService goodsService;

    @Autowired
    GoodsVOWrapper goodsVOWrapper;

    @Override
    public Page<GoodsVO> getGoodsList(String keyword, Pageable pageable){
        Page<Goods> goods=goodsService.getGoods(keyword,pageable);
        return goods.map(new Converter<Goods, GoodsVO>() {
            @Override
            public GoodsVO convert(Goods goods) {
                return goodsVOWrapper.wrap(goods);
            }
        });
    }

    @Override
    public GoodsVO getGoods(Long goodsId){
        Goods goods=goodsService.getGoodById(goodsId);
        if(goods==null){
            throw new HttpBadRequestException("good not exists");
        }
        return goodsVOWrapper.wrap(goods);
    }

    @Override
    public Long createGoods(GoodsVO goodsVO){
        Goods goods=goodsVOWrapper.unwrap(goodsVO);
        goods=goodsService.createGood(goods);
        return goods.getId();
    }

    @Override
    public void updateGoods(GoodsVO goodsVO){
        Goods goods=goodsService.getGoodById(goodsVO.getId());
        if(goods==null){
            throw new HttpBadRequestException("good not exists");
        }
        goodsService.updateInfo(goods,goodsVO);
    }

    @Override
    public void deleteGoods(Long goodsId){
        Goods goods=goodsService.getGoodById(goodsId);
        if(goods==null){
            throw new HttpBadRequestException("good not exists");
        }
        goodsService.deleteGoods(goods);
    }
}
