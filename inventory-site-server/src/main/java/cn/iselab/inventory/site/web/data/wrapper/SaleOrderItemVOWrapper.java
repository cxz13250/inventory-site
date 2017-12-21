package cn.iselab.inventory.site.web.data.wrapper;

import cn.iselab.inventory.site.model.SaleOrderItem;
import cn.iselab.inventory.site.service.GoodsService;
import cn.iselab.inventory.site.web.data.SaleOrderItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午7:23 2017/12/20
 * @Modified By:
 */
@Service
public class SaleOrderItemVOWrapper extends BaseWrapper<SaleOrderItemVO,SaleOrderItem>{

    @Autowired
    GoodsService goodsService;

    @Override
    public SaleOrderItemVO wrap(SaleOrderItem item){
        SaleOrderItemVO vo = new SaleOrderItemVO();
        vo.setGoodId(item.getGoodId());
        vo.setExtra(item.getExtra());
        vo.setPrice(item.getPrice());
        vo.setSum(item.getSum());
        vo.setTotal(item.getTotal());
        vo.setGoodModel(goodsService.getGoodById(item.getGoodId()).getModel());
        vo.setGoodName(goodsService.getGoodById(item.getGoodId()).getName());
        return vo;
    }

    @Override
    public SaleOrderItem unwrap(SaleOrderItemVO vo){
        SaleOrderItem item = new SaleOrderItem();
        item.setExtra(vo.getExtra());
        item.setGoodId(vo.getGoodId());
        item.setPrice(vo.getPrice());
        item.setSum(vo.getSum());
        item.setTotal(vo.getTotal());
        return item;
    }
}
