package cn.iselab.inventory.site.web.data.wrapper;

import cn.iselab.inventory.site.model.Goods;
import cn.iselab.inventory.site.service.CategoryService;
import cn.iselab.inventory.site.web.data.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午9:05 2017/12/4
 * @Modified By:
 */
@Service
public class GoodsVOWrapper extends BaseWrapper<GoodsVO,Goods> {

    @Autowired
    CategoryService categoryService;

    @Override
    public GoodsVO wrap(Goods goods){
        GoodsVO vo=new GoodsVO();
        vo.setCostPrice(goods.getCostPrice());
        vo.setCategoryId(goods.getCategory());
        vo.setCategoryName(categoryService.getCategory(goods.getCategory()).getName());
        vo.setCurrentCostPrice(goods.getCurrentCostPrice());
        vo.setCurrentRetailPrice(goods.getCurrentRetailPrice());
        vo.setName(goods.getName());
        vo.setInventory(goods.getInventory());
        vo.setModel(goods.getModel());
        vo.setRetailPrice(goods.getRetailPrice());
        vo.setId(goods.getId());
        return vo;
    }

    @Override
    public Goods unwrap(GoodsVO vo){
        Goods goods=new Goods();
        goods.setCategory(vo.getCategoryId());
        goods.setModel(vo.getModel());
        goods.setRetailPrice(vo.getRetailPrice());
        goods.setCostPrice(vo.getCostPrice());
        goods.setName(vo.getName());
        goods.setCurrentCostPrice(vo.getCurrentCostPrice());
        goods.setCurrentRetailPrice(vo.getCurrentRetailPrice());
        goods.setInventory(vo.getInventory());
        return goods;
    }
}
