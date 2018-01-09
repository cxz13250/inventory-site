package cn.iselab.inventory.site.web.data.wrapper;

import cn.iselab.inventory.site.model.Category;
import cn.iselab.inventory.site.model.Goods;
import cn.iselab.inventory.site.service.GoodsService;
import cn.iselab.inventory.site.web.data.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午1:54 2017/12/5
 * @Modified By:
 */
@Service
public class CategoryVOWrapper extends BaseWrapper<CategoryVO,Category> {

    @Autowired
    GoodsService goodsService;

    @Override
    public CategoryVO wrap(Category category){
        CategoryVO vo=new CategoryVO();
        if (category.getCreateTime()!=null)
            vo.setCreateTime(category.getCreateTime().getTime());
        vo.setDescription(category.getDescription());
        vo.setName(category.getName());
        vo.setSuperId(category.getSuperId());
        vo.setId(category.getId());
        List<Goods> goods=goodsService.getGoodByCategory(category.getId());
        if(goods==null||goods.isEmpty()){
            vo.setCanDelete(true);
        }else {
            vo.setCanDelete(false);
        }
        return vo;
    }

    @Override
    public Category unwrap(CategoryVO vo){
        Category category=new Category();
        category.setName(vo.getName());
        category.setDescription(vo.getDescription());
        category.setSuperId(vo.getSuperId());
        return category;
    }
}
