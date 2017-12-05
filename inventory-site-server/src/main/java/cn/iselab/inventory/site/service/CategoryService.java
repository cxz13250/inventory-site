package cn.iselab.inventory.site.service;

import cn.iselab.inventory.site.model.Category;
import cn.iselab.inventory.site.web.data.CategoryVO;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午2:44 2017/11/6
 * @Modified By:
 */
public interface CategoryService {

    Category createCategory(Category category);

    List<Category> getCategories();

    Category getCategory(long id);

    List<Category> getCategoryByName(String name);

    void deleteCategory(Category category);

    void updateCategory(Category category, CategoryVO vo);
}
