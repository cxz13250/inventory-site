package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.common.constanst.DeleteStatus;
import cn.iselab.inventory.site.dao.CategoryDao;
import cn.iselab.inventory.site.model.Category;
import cn.iselab.inventory.site.service.CategoryService;
import cn.iselab.inventory.site.web.data.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:45 2017/11/6
 * @Modified By:
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Override
    public Category createCategory(Category category){
        category.setCreateTime(new Timestamp(System.currentTimeMillis()));
        category.setDelete(DeleteStatus.IS_NOT_DELETE);
        return categoryDao.save(category);
    }

    @Override
    public List<Category> getCategories(){
        return categoryDao.findAllCategory();
    }

    @Override
    public Category getCategory(long id){
        return categoryDao.findOne(id);
    }

    @Override
    public List<Category> getCategoryByName(String name){
        List<Category> categories = categoryDao.findByName(name);
        return categories;
    }

    @Override
    public void deleteCategory(Category category){
        category.setDelete(DeleteStatus.IS_DELETE);
        categoryDao.save(category);
    }

    @Override
    public void updateCategory(Category category, CategoryVO vo){
        category.setDescription(vo.getDescription());
        category.setName(vo.getName());
        category.setSuperId(vo.getSuperId());
        categoryDao.save(category);
    }

}
