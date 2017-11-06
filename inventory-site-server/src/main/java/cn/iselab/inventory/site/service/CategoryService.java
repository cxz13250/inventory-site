package cn.iselab.inventory.site.service;

import cn.iselab.inventory.site.model.Category;

import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:44 2017/11/6
 * @Modified By:
 */
public interface CategoryService {

    void createCategory(Category category);

    List<Category> getCategories();

    Category getCategory(long id);

    void deleteCategory(Category category);

    void updateCategory(Category category);
}
