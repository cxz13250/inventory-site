package cn.iselab.inventory.site.service.impl;

import cn.iselab.inventory.site.dao.CategoryDao;
import cn.iselab.inventory.site.model.Category;
import cn.iselab.inventory.site.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void createCategory(Category category){
        categoryDao.save(category);
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
    public void deleteCategory(Category category){
        categoryDao.delete(category);
    }

    @Override
    public void updateCategory(Category category){
        categoryDao.save(category);
    }

}
