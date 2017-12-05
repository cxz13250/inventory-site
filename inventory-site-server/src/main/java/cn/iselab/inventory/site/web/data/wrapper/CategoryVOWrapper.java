package cn.iselab.inventory.site.web.data.wrapper;

import cn.iselab.inventory.site.model.Category;
import cn.iselab.inventory.site.web.data.CategoryVO;
import org.springframework.stereotype.Service;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午1:54 2017/12/5
 * @Modified By:
 */
@Service
public class CategoryVOWrapper extends BaseWrapper<CategoryVO,Category> {

    @Override
    public CategoryVO wrap(Category category){
        CategoryVO vo=new CategoryVO();
        if (category.getCreateTime()!=null)
            vo.setCreateTime(category.getCreateTime().getTime());
        vo.setDescription(category.getDescription());
        vo.setName(category.getName());
        vo.setSuperId(category.getSuperId());
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
