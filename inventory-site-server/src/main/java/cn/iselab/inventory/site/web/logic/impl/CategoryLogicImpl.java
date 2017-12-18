package cn.iselab.inventory.site.web.logic.impl;

import cn.iselab.inventory.site.model.Category;
import cn.iselab.inventory.site.service.CategoryService;
import cn.iselab.inventory.site.web.data.CategoryVO;
import cn.iselab.inventory.site.web.data.wrapper.CategoryVOWrapper;
import cn.iselab.inventory.site.web.exception.HttpBadRequestException;
import cn.iselab.inventory.site.web.logic.CategoryLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午1:42 2017/12/5
 * @Modified By:
 */
@Service
public class CategoryLogicImpl implements CategoryLogic {

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryVOWrapper categoryVOWrapper;

    @Override
    public List<CategoryVO> getCategorys(){
        List<Category> categories=categoryService.getCategories();
        List<CategoryVO> vos=new ArrayList<>();
        categories.forEach(category -> {
            CategoryVO vo=categoryVOWrapper.wrap(category);
            vo.setSuperName(categoryService.getCategory(category.getSuperId()).getName());
            vos.add(vo);
        });
        return vos;
    }

    @Override
    public CategoryVO getCategory(Long categoryId){
        Category category=categoryService.getCategory(categoryId);
        if(category==null){
            throw new HttpBadRequestException("category not exists");
        }
        CategoryVO vo=categoryVOWrapper.wrap(category);
        vo.setSuperName(categoryService.getCategory(category.getId()).getName());
        return vo;
    }

    @Override
    public Long createCategory(CategoryVO vo){
        List<Category> categories=categoryService.getCategoryByName(vo.getName());
        if(categories!=null&&categories.size()>0){
            throw new IllegalArgumentException("category name already exists");
        }
        Category category=categoryVOWrapper.unwrap(vo);
        category=categoryService.createCategory(category);
        return category.getId();
    }

    @Override
    public void updateCategory(CategoryVO vo){
        Category category=categoryService.getCategory(vo.getId());
        if(category==null){
            throw new HttpBadRequestException("category not exists");
        }
        categoryService.updateCategory(category,vo);
    }

    @Override
    public void deleteCategory(Long categoryId){
        Category category=categoryService.getCategory(categoryId);
        if(category==null){
            throw new HttpBadRequestException("category not exists");
        }
        categoryService.deleteCategory(category);
    }

    @Override
    public List<CategoryVO> getCategorysForGood(){
        List<Category> categories=categoryService.getCategoriesForGood();
        List<CategoryVO> vos=new ArrayList<>();
        categories.forEach(category -> {
            CategoryVO vo=categoryVOWrapper.wrap(category);
            vo.setSuperName(categoryService.getCategory(category.getSuperId()).getName());
            vos.add(vo);
        });
        return vos;
    }
}
